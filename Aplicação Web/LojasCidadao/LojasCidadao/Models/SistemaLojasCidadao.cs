using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class SistemaLojasCidadao
    {
        NpgsqlConnection con;
        ListaEntidade entidades;
        ListaServico servicos;
        ListaLojaCidacao lojas;
        ListaBalcao balcoes;

        public SistemaLojasCidadao()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);

            entidades = new ListaEntidade(con);
            servicos = new ListaServico(con);
            lojas = new ListaLojaCidacao(con);
            balcoes = new ListaBalcao(con);
        }

        public List<Entidade> getTodasEntidades()
        {
            return entidades.getTodasEntidades();
        }

        public Entidade getEntidadePorID(int id)
        {
            return entidades.getEntidadePorID(id);
        }

        public List<Entidade> getEntidadesPorNome(String nome)
        {
            return entidades.getEntidadesPorNome(nome);
        }

        public List<Entidade> getEntidadesPorSigla(String sigla)
        {
            return entidades.getEntidadesPorSigla(sigla);
        }

        public void addEntidade(Entidade e)
        {
            entidades.addEntidade(e);
        }

        public void deleteEntidade(Entidade e)
        {
            entidades.deleteEntidade(e);
        }

        public void updateEntidade(Entidade e)
        {
            entidades.updateEntidade(e);
        }

        public List<String> listaEntidadesAsStrings()
        {
            return entidades.listaEntidadesAsStrings();
        }

        public List<Servico> getTodosServicos()
        {
            return servicos.getTodosServicos();
        }

        public Servico getServicoPorID(int id)
        {
            return servicos.getServicoPorID(id);
        }

        public List<Servico> getServicosPorEntidade(int id)
        {
            return servicos.getServicosPorEntidade(id);
        }

        public List<Servico> getServicosPorNome(String nome)
        {
            return servicos.getServicosPorNome(nome);
        }

        public List<Servico> getServicosPorTipo(String tipo)
        {
            return servicos.getServicosPorTipo(tipo);
        }

        public String nomeEntidade(int id)
        {
            return servicos.nomeEntidade(id);
        }

        public void addServico(Servico s)
        {
            servicos.addServico(s);
        }

        public void deleteServico(Servico s)
        {
            servicos.deleteServico(s);
        }

        public void updateServico(Servico s)
        {
            servicos.updateServico(s);
        }

        public List<String> listaNomesServicosPorEntidade(int id)
        {
            return servicos.listaNomesServicosPorEntidade(id);
        }

        public List<LojaCidadao> getTodasLojas()
        {
            return lojas.getTodasLojas();
        }

        public LojaCidadao getLojaPorID(int id)
        {
            return lojas.getLojaPorID(id);
        }

        public void addLoja(LojaCidadao l)
        {
            lojas.addLoja(l);
        }

        public void deleteLoja(LojaCidadao l)
        {
            lojas.deleteLoja(l);
        }

        public void updateLoja(LojaCidadao l)
        {
            lojas.updateLoja(l);
        }

        public String nomeDistrito(int id)
        {
            return lojas.nomeDistrito(id);
        }

        public String nomeConcelho(int id)
        {
            return lojas.nomeConcelho(id);
        }

        public List<String> getTodosConcelhos()
        {
            return lojas.getTodosConcelhos();
        }

        public List<LojaCidadao> getLojasPorNome(String nome)
        {
            return lojas.getLojasPorNome(nome);
        }

        public List<LojaCidadao> getLojasPorMorada(String morada)
        {
            return lojas.getLojasPorMorada(morada);
        }

        public List<LojaCidadao> getLojasPorConcelho(int concelho, int distrito)
        {
            return lojas.getLojasPorConcelho(concelho, distrito);
        }

        public List<Balcao> getBalcoesDeLoja(int id)
        {
            return balcoes.getBalcoesDeLoja(id);
        }

        public Balcao getBalcaoPorID(int idloja, int identidade)
        {
            return balcoes.getBalcaoPorID(idloja, identidade);
        }

        public void addBalcao(Balcao b)
        {
            balcoes.addBalcao(b);
        }

        public void deleteBalcao(Balcao b)
        {
            balcoes.deleteBalcao(b);
        }

        public void updateBalcao(Balcao b)
        {
            balcoes.updateBalcao(b);
        }

        public List<RelacaoBalcaoServico> getServicosDeBalcao(int idloja, int identidade)
        {
            return balcoes.getServicosDeBalcao(idloja, identidade);
        }

        public RelacaoBalcaoServico getServicoDeBalcaoPorID(int idloja, int identidade, int idservico)
        {
            return balcoes.getServicoDeBalcaoPorID(idloja, identidade, idservico);
        }

        public void addServicoABalcao(RelacaoBalcaoServico r)
        {
            balcoes.addServicoABalcao(r);
        }

        public void deleteServicoDeBalcao(RelacaoBalcaoServico r)
        {
            balcoes.deleteServicoDeBalcao(r);
        }

        public void updateServicoDeBalcao(RelacaoBalcaoServico r)
        {
            balcoes.updateServicoDeBalcao(r);
        }
    }
}