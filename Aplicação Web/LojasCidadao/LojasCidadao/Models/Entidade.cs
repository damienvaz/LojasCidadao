using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class Entidade
    {
        private int id;
        private String nome;
        private String sigla;
        private String morada;
        private String telefone;
        private String fax;
        private String email;
        private String url;
        private String site;

        public Entidade()
        {
            this.id = 0;
            this.nome = "";
            this.sigla = "";
            this.morada = "";
            this.telefone = "";
            this.fax = "";
            this.email = "";
            this.url = "";
            this.site = "";
        }

        public Entidade(int id, String nome, String sigla, String morada, String telefone, String fax, String email, String url, String site)
        {
            this.id = id;
            this.nome = nome;
            this.sigla = sigla;
            this.morada = morada;
            this.telefone = telefone;
            this.fax = fax;
            this.email = email;
            this.url = url;
            this.site = site;
        }

        public int getID()
        {
            return id;
        }

        public String getNome()
        {
            return nome;
        }

        public String getSigla()
        {
            return sigla;
        }

        public String getMorada()
        {
            return morada;
        }

        public String getTelefone()
        {
            return telefone;
        }

        public String getFax()
        {
            return fax;
        }

        public String getEmail()
        {
            return email;
        }

        public String getUrl()
        {
            return url;
        }

        public String getSite()
        {
            return site;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public void setMorada(String morada)
        {
            this.morada = morada;
        }

        public void setSigla(String sigla)
        {
            this.sigla = sigla;
        }

        public void setTelefone(String tel)
        {
            this.telefone = tel;
        }

        public void setFax(String fax)
        {
            this.fax = fax;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public void setSite(String site)
        {
            this.site = site;
        }

        public IEnumerable<RuleViolation> GetRuleViolations()
        {
            if (String.IsNullOrEmpty(nome))
                yield return new RuleViolation("Nome necessário", "Nome");
            if (String.IsNullOrEmpty(sigla))
                yield return new RuleViolation("Sigla necessária", "Sigla");
            if (String.IsNullOrEmpty(morada))
                yield return new RuleViolation("Morada necessária", "Morada");
            yield break;
        }

        public bool IsValid
        {
            get { return (GetRuleViolations().Count() == 0); }
        }
    }
}