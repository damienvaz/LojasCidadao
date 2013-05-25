using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using System.Web.Security;
using LojasCidadao.Models;

namespace LojasCidadao.Controllers
{
    public class AutenticarController : Controller
    {
        SistemaLojasCidadao lista = new SistemaLojasCidadao();
   
        //
        // GET: /LogIn/

        public ActionResult LogOn()
        {
            return View();
        }

        [HttpPost]
        public ActionResult LogOn(string returnUrl)
        {
            var nick = Request.Form["Nick"];
            var pass = Request.Form["Password"];
            var remember = Request.Form["RememberMe"];
            bool e;
            char[] delimiterChars2 = { ',' };
            string[] palavras2 = remember.Split(delimiterChars2);
            e = Convert.ToBoolean(palavras2[0]);
            Funcionario f = lista.autenticar(nick);
            if (ModelState.IsValid)
            {
                if (f != null && pass == f.getPassword())
                {
                    FormsAuthentication.SetAuthCookie(f.getNick(), e);
                    if (Url.IsLocalUrl(returnUrl) && returnUrl.Length > 1 && returnUrl.StartsWith("/")
                        && !returnUrl.StartsWith("//") && !returnUrl.StartsWith("/\\"))
                    {
                        return Redirect(returnUrl);
                    }
                    else
                    {
                        return RedirectToAction("Index", "Home");
                    }
                }
                else
                {
                    ModelState.AddModelError("", "Nick ou password incorretos.");
                }
            }

            return View();
        }

        public ActionResult LogOff()
        {
            HttpCookie cookie1 = new HttpCookie(FormsAuthentication.FormsCookieName, "");
            cookie1.Expires = DateTime.Now.AddYears(-1);
            cookie1.Path = FormsAuthentication.FormsCookiePath;
            cookie1.HttpOnly = true;
            Response.Cookies.Add(cookie1);

            return RedirectToAction("Index", "Home");
        }

    }
}
