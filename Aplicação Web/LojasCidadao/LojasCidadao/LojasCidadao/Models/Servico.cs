using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class Servico
    {
        private int id;
        private int id_entidade;
        private String nome;
        private String descricao;
        private String tipo;
        private String url;
        private bool estado;

        public Servico()
        {
            this.id = 0;
            this.id_entidade = 0;
            this.nome = "";
            this.descricao = "";
            this.tipo = "";
            this.url = "";
            this.estado = true;
        }

        public Servico(int id, int id_entidade, String nome, String descricao, String tipo, String url, bool estado)
        {
            this.id = id;
            this.id_entidade = id_entidade;
            this.nome = nome;
            this.descricao = descricao;
            this.tipo = tipo;
            this.url = url;
            this.estado = estado;
        }

        public int getID()
        {
            return id;
        }

        public int getEntidadeID()
        {
            return id_entidade;
        }

        public String getNome()
        {
            return nome;
        }

        public String getDescricao()
        {
            return descricao;
        }

        public String getTipo()
        {
            return tipo;
        }

        public String getUrl()
        {
            return url;
        }

        public bool isAtivo()
        {
            return estado;
        }

        public void setEntidadeID(int id)
        {
            this.id_entidade = id;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public void setDescricao(String desc)
        {
            this.descricao = desc;
        }

        public void setTipo(String tipo)
        {
            this.tipo = tipo;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public void setEstado(bool estado)
        {
            this.estado = estado;
        }

        public IEnumerable<RuleViolation> GetRuleViolations()
        {
            if (String.IsNullOrEmpty(nome))
                yield return new RuleViolation("Nome necessário", "Nome");
            if (id_entidade <= 0)
                yield return new RuleViolation("Entidade necessária", "Entidade");
            yield break;
        }

        public bool IsValid
        {
            get { return (GetRuleViolations().Count() == 0); }
        }
    }
}