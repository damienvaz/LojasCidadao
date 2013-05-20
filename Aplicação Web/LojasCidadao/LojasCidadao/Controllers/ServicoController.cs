using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LojasCidadao.Models;

namespace LojasCidadao.Controllers
{
    public class ServicoController : Controller
    {
        SistemaLojasCidadao lista = new SistemaLojasCidadao();
        //
        // GET: /Servico/

        [Authorize]
        public ActionResult Index()
        {
            var servicos = lista.getTodosServicos();
            return View("Index",servicos);
        }

        [Authorize]
        public ActionResult Procura()
        {
            List<String> entidades = lista.listaEntidadesAsStrings();
            ViewData["Entidades"] = new SelectList(entidades);
            return View("Procura");
        }

        [Authorize]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Procura(FormCollection formValues)
        {
            var nome = Request.Form["Nome"];
            var tipo = Request.Form["Tipo"];
            var entidade = Request.Form["Entidade"];
            var servicos = new List<Servico>();
            try
            {
                if (!String.IsNullOrEmpty(nome))
                {
                    servicos = lista.getServicosPorNome(nome);
                    return View("ResultadosProcura", servicos);
                }
                else if (!String.IsNullOrEmpty(tipo))
                {
                    servicos = lista.getServicosPorTipo(tipo);
                    return View("ResultadosProcura", servicos);
                }
                else if (!String.IsNullOrEmpty(entidade))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = entidade.Split(delimiterChars);
                    int id_entidade = Convert.ToInt32(palavras[0]);
                    servicos = lista.getServicosPorEntidade(id_entidade);
                    return View("ResultadosProcura", servicos);
                }
                else
                {
                    return View("ResultadosProcura", servicos);
                }
            }
            catch
            {
                return View("ResultadosProcura", servicos);
            }
        }

        [Authorize]
        public ActionResult Detalhes(String id)
        {
            try
            {
                int id_s = Convert.ToInt32(id);
                Servico s = lista.getServicoPorID(id_s);
                if (s == null)
                {
                    return View("NotFound");
                }
                else
                {
                    ViewData["Entidade"] = lista.nomeEntidade(id_s);
                    return View(s);
                }
            }
            catch
            {
                return View("Error");
            }
        }

        [Authorize]
        public ActionResult Edit(String id)
        {
            try
            {
                int id_s = Convert.ToInt32(id);
                List<String> entidades = lista.listaEntidadesAsStrings();
                Servico s = lista.getServicoPorID(id_s);
                if (s == null)
                {
                    return View("NotFound");
                }
                else
                {
                    ViewData["Entidades"] = new SelectList(entidades);
                    return View(s);
                }
            }
            catch
            {
                return View("Error");
            }
        }

        [Authorize]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(int id, FormCollection formValues)
        {
            List<String> entidades = lista.listaEntidadesAsStrings();
            ViewData["Entidades"] = new SelectList(entidades);

            Servico s = lista.getServicoPorID(id);
            String entidade = Request.Form["Entidade"];
            int id_entidade = 0;
            if (!String.IsNullOrEmpty(entidade))
            {
                char[] delimiterChars = { '-' };
                string[] palavras = entidade.Split(delimiterChars);
                id_entidade = Convert.ToInt32(palavras[0]);
            }
            s.setEntidadeID(id_entidade);
            s.setNome(Request.Form["Nome"]);
            s.setDescricao(Request.Form["Descrição"]);
            s.setTipo(Request.Form["Tipo"]);
            s.setUrl(Request.Form["URL"]);
            if (s.IsValid)
            {
                lista.updateServico(s);
                return RedirectToAction("Index");
            }
            else
            {
                var errors = s.GetRuleViolations();
                foreach (var issue in errors)
                {
                    ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                }
                return View(s);
            }            
        }

        [Authorize]
        public ActionResult Create()
        {
            Servico s = new Servico();
            List<String> entidades = lista.listaEntidadesAsStrings();
            ViewData["Entidades"] = new SelectList(entidades);
            return View(s);
        }

        [Authorize]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Create(Servico s)
        {
            List<String> entidades = lista.listaEntidadesAsStrings();
            ViewData["Entidades"] = new SelectList(entidades);
            try
            {
                String entidade = Request.Form["Entidade"];
                int id_entidade = 0;
                if (!String.IsNullOrEmpty(entidade))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = entidade.Split(delimiterChars);
                    id_entidade = Convert.ToInt32(palavras[0]);
                }
                s.setEntidadeID(id_entidade);
                s.setNome(Request.Form["Nome"]);
                s.setDescricao(Request.Form["Descrição"]);
                s.setTipo(Request.Form["Tipo"]);
                s.setUrl(Request.Form["URL"]);
                if (s.IsValid)
                {
                    lista.addServico(s);
                    return RedirectToAction("Index");
                }
                else
                {
                    var errors = s.GetRuleViolations();
                    foreach (var issue in errors)
                    {
                        ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                    }
                    return View(s);
                }
            }
            catch
            {
                return RedirectToAction("Create");
            }
        }

        [Authorize]
        public ActionResult Delete(String id)
        {
            try
            {
                int id_s = Convert.ToInt32(id);
                Servico s = lista.getServicoPorID(id_s);
                if (s == null)
                {
                    return View("NotFound");
                }
                else return View(s);
            }
            catch
            {
                return View("Error");
            }
        }

        [Authorize]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(int id, string confirmButton)
        {
            Servico s = lista.getServicoPorID(id);
            lista.deleteServico(s);
            return View("Deleted");
        }

    }
}
