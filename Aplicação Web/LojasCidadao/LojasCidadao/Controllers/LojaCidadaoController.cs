using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LojasCidadao.Models;

namespace LojasCidadao.Controllers
{
    public class LojaCidadaoController : Controller
    {
        SistemaLojasCidadao lista = new SistemaLojasCidadao();
        //
        // GET: /LojaCidadao/

        public ActionResult Index()
        {
            var lojas = lista.getTodasLojas();
            return View("Index", lojas);
        }

        public ActionResult Procura()
        {
            List<String> concelhos = lista.getTodosConcelhos();
            ViewData["Concelhos"] = new SelectList(concelhos);
            return View("Procura");
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Procura(FormCollection formValues)
        {
            var nome = Request.Form["Nome"];
            var morada = Request.Form["Morada"];
            var concelho = Request.Form["Concelho"];
            var lojas = new List<LojaCidadao>();
            try
            {
                if (!String.IsNullOrEmpty(nome))
                {
                    lojas = lista.getLojasPorNome(nome);
                    return View("ResultadosProcura", lojas);
                }
                else if (!String.IsNullOrEmpty(morada))
                {
                    lojas = lista.getLojasPorMorada(morada);
                    return View("ResultadosProcura", lojas);
                }
                else if (!String.IsNullOrEmpty(concelho))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = concelho.Split(delimiterChars);
                    int id_concelho = Convert.ToInt32(palavras[0]);
                    int id_distrito = Convert.ToInt32(palavras[1]);
                    lojas = lista.getLojasPorConcelho(id_concelho, id_distrito);
                    return View("ResultadosProcura", lojas);
                }
                else
                {
                    return View("ResultadosProcura", lojas);
                }
            }
            catch
            {
                return View("ResultadosProcura", lojas);
            }
        }

        public ActionResult Detalhes(int id)
        {
            LojaCidadao l = lista.getLojaPorID(id);
            if (l == null)
            {
                return View("NotFound");
            }
            else
            {
                ViewData["Distrito"] = lista.nomeDistrito(id);
                ViewData["Concelho"] = lista.nomeConcelho(id);
                return View(l);
            }
        }

        public ActionResult Mapa(int id)
        {
            LojaCidadao l = lista.getLojaPorID(id);
            if (l == null)
            {
                return View("NotFound");
            }
            else
            {
                ViewData["Lat"] = Convert.ToDouble(l.getLatitude());
                ViewData["Lon"] = Convert.ToDouble(l.getLongitude());
                return View(l);
            }
        }

        public ActionResult Edit(int id)
        {
            List<String> lojas = lista.getTodosConcelhos();
            LojaCidadao l = lista.getLojaPorID(id);
            if (l == null)
            {
                return View("NotFound");
            }
            else
            {
                ViewData["Concelhos"] = new SelectList(lojas);
                return View(l);
            }
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(int id, FormCollection formValues)
        {
            List<String> lojas = lista.getTodosConcelhos();
            ViewData["Concelhos"] = new SelectList(lojas);

            LojaCidadao l = lista.getLojaPorID(id);
            String concelho = Request.Form["Concelho"];
            int id_distrito = 0;
            int id_concelho = 0;
            if (!String.IsNullOrEmpty(concelho))
            {
                char[] delimiterChars = { '-' };
                string[] palavras = concelho.Split(delimiterChars);
                id_concelho = Convert.ToInt32(palavras[0]);
                id_distrito = Convert.ToInt32(palavras[1]);
            }

            String estado = Request.Form["Estado"];
            bool e;
            char[] delimiterChars2 = { ',' };
            string[] palavras2 = estado.Split(delimiterChars2);
            e = Convert.ToBoolean(palavras2[0]);

            l.setNome(Request.Form["Nome"]);
            l.setMorada(Request.Form["Morada"]);
            l.setCod_postal(Request.Form["CodPostal"]);
            l.setId_conselho(id_concelho);
            l.setId_distrito(id_distrito);
            l.setLatitude(Request.Form["Latitude"]);
            l.setLongitude(Request.Form["Longitude"]);
            l.setTelefone(Request.Form["Telefone"]);
            l.setEstado(e);
            if (l.IsValid)
            {
                lista.updateLoja(l);
                return RedirectToAction("Index");
            }
            else
            {
                var errors = l.GetRuleViolations();
                foreach (var issue in errors)
                {
                    ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                }
                return View(l);
            }
        }

        public ActionResult Create()
        {
            List<String> lojas = lista.getTodosConcelhos();
            LojaCidadao l = new LojaCidadao();
            ViewData["Concelhos"] = new SelectList(lojas);
            return View(l);
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Create(LojaCidadao l)
        {
            List<String> lojas = lista.getTodosConcelhos();
            ViewData["Concelhos"] = new SelectList(lojas);

            try
            {
                String concelho = Request.Form["Concelho"];
                int id_distrito = 0;
                int id_concelho = 0;
                if (!String.IsNullOrEmpty(concelho))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = concelho.Split(delimiterChars);
                    id_concelho = Convert.ToInt32(palavras[0]);
                    id_distrito = Convert.ToInt32(palavras[1]);
                }

                String estado = Request.Form["Estado"];
                bool e;
                char[] delimiterChars2 = { ',' };
                string[] palavras2 = estado.Split(delimiterChars2);
                e = Convert.ToBoolean(palavras2[0]);

                l.setNome(Request.Form["Nome"]);
                l.setMorada(Request.Form["Morada"]);
                l.setCod_postal(Request.Form["CodPostal"]);
                l.setId_conselho(id_concelho);
                l.setId_distrito(id_distrito);
                l.setLatitude(Request.Form["Latitude"]);
                l.setLongitude(Request.Form["Longitude"]);
                l.setTelefone(Request.Form["Telefone"]);
                l.setEstado(e);
                if (l.IsValid)
                {
                    lista.addLoja(l);
                    return RedirectToAction("Index");
                }
                else
                {
                    var errors = l.GetRuleViolations();
                    foreach (var issue in errors)
                    {
                        ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                    }
                    return View(l);
                }
            }
            catch
            {
                return RedirectToAction("Create");
            }
        }

        public ActionResult Delete(int id)
        {
            LojaCidadao l = lista.getLojaPorID(id);
            if (l == null)
            {
                return View("NotFound");
            }
            else return View(l);
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(int id, string confirmButton)
        {
            LojaCidadao l = lista.getLojaPorID(id);
            lista.deleteLoja(l);
            return View("Deleted");
        }


        //--------------BALCOES----------------------------------------------------


        public ActionResult Balcoes(int id)
        {
            ViewData["Loja"] = id;
            var balcoes = lista.getBalcoesDeLoja(id);
            return View("Balcoes", balcoes);
        }

        public ActionResult CreateBalcao(int id)
        {
            LojaCidadao l = lista.getLojaPorID(id);
            if (l == null)
            {
                return View("NotFound");
            }
            else
            {
                Balcao b = new Balcao(l.getId(), 0, true, "");
                List<String> entidades = lista.listaEntidadesAsStrings();
                ViewData["Entidades"] = new SelectList(entidades);
                ViewData["LojaID"] = l.getId();
                return View(b);
            }
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult CreateBalcao(Balcao b)
        {
            String lojaID = Request.Form["Loja"];
            String entidade = Request.Form["Entidade"];
            int id_entidade = 0;
            int id_loja = Convert.ToInt32(lojaID);
            List<String> entidades = lista.listaEntidadesAsStrings();
            ViewData["Entidades"] = new SelectList(entidades);
            ViewData["LojaID"] = id_loja;

            try
            {
                if (!String.IsNullOrEmpty(entidade))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = entidade.Split(delimiterChars);
                    id_entidade = Convert.ToInt32(palavras[0]);
                }

                String estado = Request.Form["Estado"];
                bool e;
                char[] delimiterChars2 = { ',' };
                string[] palavras2 = estado.Split(delimiterChars2);
                e = Convert.ToBoolean(palavras2[0]);

                b.setLojaID(id_loja);
                b.setEntidadeID(id_entidade);
                b.setEstado(e);
                if (b.IsValid)
                {
                    lista.addBalcao(b);
                    return RedirectToAction("Index");
                }
                else
                {
                    var errors = b.GetRuleViolations();
                    foreach (var issue in errors)
                    {
                        ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                    }
                    return View(b);
                }
            }
            catch
            {
                return RedirectToAction("Index");
            }
        }

        public ActionResult EditBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            Balcao b = lista.getBalcaoPorID(id_loja, id_entidade);
            ViewData["Loja"] = id_loja;
            if (b == null)
            {
                return View("NotFoundBalcao");
            }
            else return View(b);
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult EditBalcao(String id, FormCollection formValues)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            Balcao b = lista.getBalcaoPorID(id_loja, id_entidade);

            String estado = Request.Form["Estado"];
            bool e;
            char[] delimiterChars2 = { ',' };
            string[] palavras2 = estado.Split(delimiterChars2);
            e = Convert.ToBoolean(palavras2[0]);

            b.setEstado(e);
            if (b.IsValid)
            {
                lista.updateBalcao(b);
                return RedirectToAction("Index");
            }
            else
            {
                var errors = b.GetRuleViolations();
                foreach (var issue in errors)
                {
                    ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                }
                return View(b);
            }
        }

        public ActionResult DeleteBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            Balcao b = lista.getBalcaoPorID(id_loja, id_entidade);
            if (b == null)
            {
                return View("NotFoundBalcao");
            }
            else
            {
                ViewData["Loja"] = id_loja;
                return View(b);
            } 
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteBalcao(String id, string confirmButton)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            Balcao b = lista.getBalcaoPorID(id_loja, id_entidade);
            lista.deleteBalcao(b);
            return View("DeletedBalcao");
        }

        //----------SERVICOS DE BALCOES---------------------------------

        public ActionResult ServicosBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);

            ViewData["Loja"] = id_loja;
            ViewData["Entidade"] = id_entidade;
            var servicos = lista.getServicosDeBalcao(id_loja, id_entidade);
            return View("ServicosBalcao", servicos);
        }

        public ActionResult CreateServicoBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            LojaCidadao l = lista.getLojaPorID(id_loja);
            if (l == null)
            {
                return View("NotFound");
            }
            else
            {
                Balcao b = lista.getBalcaoPorID(id_loja, id_entidade);
                if (b == null)
                {
                    return View("NotFoundBalcao");
                }
                else
                {
                    RelacaoBalcaoServico r = new RelacaoBalcaoServico(id_loja, id_entidade, 0, true, "");
                    List<String> servicos = lista.listaNomesServicosPorEntidade(id_entidade);
                    ViewData["LojaID"] = id_loja;
                    ViewData["EntidadeID"] = id_entidade;
                    ViewData["Servicos"] = new SelectList(servicos);
                    return View(r);
                }
            }
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult CreateServicoBalcao(RelacaoBalcaoServico r)
        {
            String lojaID = Request.Form["Loja"];
            String entidade = Request.Form["Entidade"];
            String servico = Request.Form["Servico"];
            int id_servico = 0;
            int id_entidade = Convert.ToInt32(entidade);
            int id_loja = Convert.ToInt32(lojaID);
            List<String> servicos = lista.listaNomesServicosPorEntidade(id_entidade);
            ViewData["Servicos"] = new SelectList(servicos);
            ViewData["LojaID"] = id_loja;
            ViewData["EntidadeID"] = id_entidade;

            try
            {
                if (!String.IsNullOrEmpty(servico))
                {
                    char[] delimiterChars = { '-' };
                    string[] palavras = servico.Split(delimiterChars);
                    id_servico = Convert.ToInt32(palavras[0]);
                }

                String estado = Request.Form["Estado"];
                bool e;
                char[] delimiterChars2 = { ',' };
                string[] palavras2 = estado.Split(delimiterChars2);
                e = Convert.ToBoolean(palavras2[0]);

                r.setLojaID(id_loja);
                r.setEntidadeID(id_entidade);
                r.setServicoID(id_servico);
                r.setEstado(e);
                if (r.IsValid)
                {
                    lista.addServicoABalcao(r);
                    return RedirectToAction("Index");
                }
                else
                {
                    var errors = r.GetRuleViolations();
                    foreach (var issue in errors)
                    {
                        ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                    }
                    return View(r);
                }
            }
            catch
            {
                return RedirectToAction("Index");
            }
        }

        public ActionResult EditServicoBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            int id_servico = Convert.ToInt32(palavras[2]);
            RelacaoBalcaoServico r = lista.getServicoDeBalcaoPorID(id_loja, id_entidade, id_servico);
            ViewData["Loja"] = id_loja;
            ViewData["Entidade"] = id_entidade;
            if (r == null)
            {
                return View("NotFoundServicoDeBalcao");
            }
            else return View(r);
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult EditServicoBalcao(String id, FormCollection formValues)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            int id_servico = Convert.ToInt32(palavras[2]);
            RelacaoBalcaoServico r = lista.getServicoDeBalcaoPorID(id_loja, id_entidade, id_servico);

            String estado = Request.Form["Estado"];
            bool e;
            char[] delimiterChars2 = { ',' };
            string[] palavras2 = estado.Split(delimiterChars2);
            e = Convert.ToBoolean(palavras2[0]);

            r.setEstado(e);
            if (r.IsValid)
            {
                lista.updateServicoDeBalcao(r);
                return RedirectToAction("Index");
            }
            else
            {
                var errors = r.GetRuleViolations();
                foreach (var issue in errors)
                {
                    ModelState.AddModelError(issue.PropertyName, issue.ErrorMessage);
                }
                return View(r);
            }
        }

        public ActionResult DeleteServicoBalcao(String id)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            int id_servico = Convert.ToInt32(palavras[2]);
            RelacaoBalcaoServico r = lista.getServicoDeBalcaoPorID(id_loja, id_entidade, id_servico);
            if (r == null)
            {
                return View("NotFoundServicoBalcao");
            }
            else
            {
                ViewData["Loja"] = id_loja;
                ViewData["Entidade"] = id_entidade;
                return View(r);
            } 
        }

        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteServicoBalcao(String id, string confirmButton)
        {
            char[] delimiterChars = { '-' };
            string[] palavras = id.Split(delimiterChars);
            int id_loja = Convert.ToInt32(palavras[0]);
            int id_entidade = Convert.ToInt32(palavras[1]);
            int id_servico = Convert.ToInt32(palavras[2]);
            RelacaoBalcaoServico r = lista.getServicoDeBalcaoPorID(id_loja, id_entidade, id_servico);
            lista.deleteServicoDeBalcao(r);
            return View("DeletedServicoBalcao");
        }

    }
}
