using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Npgsql;

namespace LojasCidadao.Models
{
    public class DataBaseTools
    {
        public static readonly int POSTGRESQL = 1;
        private static SQLTools bd;

        public static NpgsqlConnection getConnection(DataBaseUser dbu)
        {
            NpgsqlConnection con;
            int tipoBD = dbu.getTipoBD();
            switch (tipoBD)
            {
                case 1:
                    //bd = new PostgresqlTools("webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas");
                    bd = new PostgresqlTools(dbu.getServer(), dbu.getPorta(), dbu.getUserID(), dbu.getPassword(), dbu.getDatabase());
                    con = bd.getConnection();
                    break;
                default:
                    bd = new PostgresqlTools(dbu.getServer(), dbu.getPorta(), dbu.getUserID(), dbu.getPassword(), dbu.getDatabase());
                    con = bd.getConnection();
                    break;
            }
            return con;
        }

        public static NpgsqlDataReader getResultFrom(NpgsqlConnection con, String query)
        {
            con.Open();
            NpgsqlDataReader reader;
            NpgsqlCommand command = new NpgsqlCommand(query, con);
            reader = command.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            return reader;
        }

        //-------QUERYS ENTIDADES--------------

        public static String getQueryEntidadePorID(int id)
        {
            return bd.getQueryEntidadePorID(id);
        }

        public static String getQueryTodasEntidade()
        {
            return bd.getQueryTodasEntidades();
        }

        public static String getQueryEntidadesPorNome(String nome)
        {
            return bd.getQueryEntidadesPorNome(nome);
        }

        public static String getQueryEntidadesPorSigla(String sigla)
        {
            return bd.getQueryEntidadesPorSigla(sigla);
        }

        public static String getQueryAddEntidade(Entidade e)
        {
            return bd.getQueryAddEntidade(e);
        }

        public static String getQueryDeleteEntidade(int id)
        {
            return bd.getQueryDeleteEntidade(id);
        }

        public static String getQueryUpdateEntidade(int id, Entidade nova)
        {
            return bd.getQueryUpdateEntidade(id, nova);
        }

        //---------------QUERYS SERVICOS-------------------
        public static String getQueryServicoPorID(int id)
        {
            return bd.getQueryServicoPorID(id);
        }

        public static String getQueryTodosServicos()
        {
            return bd.getQueryTodosServicos();
        }

        public static String getQueryServicosPorNome(String nome)
        {
            return bd.getQueryServicosPorNome(nome);
        }

        public static String getQueryServicosPorEntidade(int id)
        {
            return bd.getQueryServicosPorEntidade(id);
        }

        public static String getQueryServicosPorTipo(String tipo)
        {
            return bd.getQueryServicosPorTipo(tipo);
        }

        public static String getQueryAddServico(Servico s)
        {
            return bd.getQueryAddServico(s);
        }

        public static String getQueryDeleteServico(int id)
        {
            return bd.getQueryDeleteEntidade(id);
        }

        public static String getQueryUpdateServico(int id, Servico s)
        {
            return bd.getQueryUpdateServico(id, s);
        }

        public static String getQueryNomeEntidadeDeServico(int id)
        {
            return bd.getQueryNomeEntidadeDeServico(id);
        }

        //-----------QUERYS LOJAS---------------

        public static String getQueryLojaPorID(int id)
        {
            return bd.getQueryLojaPorID(id);
        }

        public static String getQueryTodasLojas()
        {
            return bd.getQueryTodasLojas();
        }

        public static String getQueryAddLoja(LojaCidadao l)
        {
            return bd.getQueryAddLoja(l);
        }

        public static String getQueryDeleteLoja(int id)
        {
            return bd.getQueryDeleteLoja(id);
        }

        public static String getQueryUpdateLoja(int id, LojaCidadao l)
        {
            return bd.getQuetyUpdateLoja(id, l);
        }

        public static String getQueryDistritoDeLoja(int id)
        {
            return bd.getQueryDistritoDeLoja(id);
        }

        public static String getQueryConcelhoDeLoja(int id)
        {
            return bd.getQueryConcelhoDeLoja(id);
        }

        public static String getQueryTodosConcelhos()
        {
            return bd.getQueryTodosConcelhos();
        }

        public static String getQueryLojasPorConcelho(int conc, int dist)
        {
            return bd.getQueryLojasPorConcelho(conc, dist);
        }

        public static String getQueryLojasPorNome(String nome)
        {
            return bd.getQueryLojasPorNome(nome);
        }

        public static String getQueryLojasPorMorada(String morada)
        {
            return bd.getQueryLojasPorMorada(morada);
        }


        //---------------QUERYS BALCOES----------------------------
        public static String getQueryBalcoesDeLoja(int id)
        {
            return bd.getQueryBalcoesDeLoja(id);
        }

        public static String getQueryBalcaoPorID(int idloja, int identidade)
        {
            return bd.getQueryBalcaoPorID(idloja, identidade);
        }

        public static String getQueryAddBalcao(Balcao b)
        {
            return bd.getQueryAddBalcao(b);
        }

        public static String getQueryDeleteBalcao(Balcao b)
        {
            return bd.getQueryDeleteBalcao(b);
        }

        public static String getQueryUpdateBalcao(Balcao b)
        {
            return bd.getQueryUpdateBalcao(b);
        }

        //------------------QUERYS RELACOES----------------------------
        public static String getQueryServicosDeBalcao(int idloja, int identidade)
        {
            return bd.getQueryServicosDeBalcao(idloja, identidade);
        }

        public static String getQueryServicoDeBalcaoPorID(int idloja, int identidade, int idservico)
        {
            return bd.getQueryServicoDeBalcaoPorID(idloja, identidade, idservico);
        }

        public static String getQueryAddServicoABalcao(RelacaoBalcaoServico r)
        {
            return bd.getQueryAddServicoABalcao(r);
        }

        public static String getQueryDeleteServicoDeBalcao(RelacaoBalcaoServico r)
        {
            return bd.getQueryDeleteServicoDeBalcao(r);
        }

        public static String getQueryUpdateServicoDeBalcao(RelacaoBalcaoServico r)
        {
            return bd.getQueryUpdateServicoDeBalcao(r);
        }
    }
}