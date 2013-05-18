using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class Balcao
    {
        private int id_loja;
        private int id_entidade;
        private String nome_entidade;
        private bool estado;

        public Balcao()
        {
            this.id_loja = 0;
            this.id_entidade = 0;
            this.nome_entidade = "";
            this.estado = true;
        }

        public Balcao(int id_loja, int id_entidade, bool estado, String nome)
        {
            this.id_loja = id_loja;
            this.id_entidade = id_entidade;
            this.nome_entidade = nome;
            this.estado = estado;
        }

        public int getLojaID()
        {
            return id_loja;
        }

        public int getEntidadeID()
        {
            return id_entidade;
        }

        public String getNomeEntidade()
        {
            return nome_entidade;
        }

        public bool isEstado()
        {
            return estado;
        }

        public void setLojaID(int id)
        {
            this.id_loja = id;
        }

        public void setEntidadeID(int id)
        {
            this.id_entidade = id;
        }

        public void setNomeEntidade(String nome)
        {
            this.nome_entidade = nome;
        }

        public void setEstado(bool estado)
        {
            this.estado = estado;
        }

        public IEnumerable<RuleViolation> GetRuleViolations()
        {
            if (id_loja == 0)
                yield return new RuleViolation("Loja necessária", "Loja");
            if (id_entidade == 0)
                yield return new RuleViolation("Entidade necessária", "Entidade");
            yield break;
        }

        public bool IsValid
        {
            get { return (GetRuleViolations().Count() == 0); }
        }
    }
}