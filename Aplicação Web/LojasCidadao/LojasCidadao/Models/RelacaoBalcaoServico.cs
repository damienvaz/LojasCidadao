using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class RelacaoBalcaoServico
    {
        private int id_loja;
        private int id_entidade;
        private int id_servico;
        private String nome_servico;
        private string tipo_servico;
        private bool estado;

        public RelacaoBalcaoServico()
        {
            this.id_loja = 0;
            this.id_entidade = 0;
            this.id_servico = 0;
            this.nome_servico = "";
            this.tipo_servico = "";
            this.estado = true;
        }

        public RelacaoBalcaoServico(int id_loja, int id_entidade, int id_servico, bool estado, String nome_servico, String tipo)
        {
            this.id_loja = id_loja;
            this.id_entidade = id_entidade;
            this.id_servico = id_servico;
            this.nome_servico = nome_servico;
            this.tipo_servico = tipo;
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

        public int getServicoID()
        {
            return id_servico;
        }

        public String getNomeServico()
        {
            return nome_servico;
        }

        public String getTipoServico()
        {
            return tipo_servico;
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

        public void setServicoID(int id)
        {
            this.id_servico = id;
        }

        public void setNomeServico(String nome)
        {
            this.nome_servico = nome;
        }

        public void setTipoServico(String tipo)
        {
            this.tipo_servico = tipo;
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
            if (id_servico == 0)
                yield return new RuleViolation("Serviço necessári0", "Serviço");
            yield break;
        }

        public bool IsValid
        {
            get { return (GetRuleViolations().Count() == 0); }
        }
    }
}