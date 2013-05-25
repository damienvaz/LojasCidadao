using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Npgsql;


namespace LojasCidadao.Models
{
    public abstract class SQLTools
    {
        public String server;
        public String porta;
        public String userID;
        public String password;
        public String database;

        public SQLTools(String server, String porta, String userID, String password, String database)
        {
            this.server = server;
            this.porta = porta;
            this.userID = userID;
            this.password = password;
            this.database = database;
        }

        public abstract NpgsqlConnection getConnection();

        //--------QUERYS ENTIDADE---------------
        public abstract String getQueryEntidadePorID(int id);

        public abstract String getQueryTodasEntidades();

        public abstract String getQueryEntidadesPorNome(String nome);

        public abstract String getQueryEntidadesPorSigla(String sigla);

        public abstract String getQueryAddEntidade(Entidade e);

        public abstract String getQueryDeleteEntidade(int id);

        public abstract String getQueryUpdateEntidade(int id, Entidade novaEntidade);

        //---------QUERYS SERVICOS-----------------
        public abstract String getQueryServicoPorID(int id);

        public abstract String getQueryTodosServicos();

        public abstract String getQueryServicosPorEntidade(int id);

        public abstract String getQueryServicosPorNome(String nome);

        public abstract String getQueryServicosPorTipo(String tipo);

        public abstract String getQueryAddServico(Servico s);

        public abstract String getQueryDeleteServico(int id);

        public abstract String getQueryUpdateServico(int id, Servico novo);

        public abstract String getQueryNomeEntidadeDeServico(int id);

        //-------------------QUERYS LOJAS--------------------
        public abstract String getQueryLojaPorID(int id);

        public abstract String getQueryTodasLojas();

        public abstract String getQueryAddLoja(LojaCidadao l);

        public abstract String getQueryDeleteLoja(int id);

        public abstract String getQuetyUpdateLoja(int id, LojaCidadao l);

        public abstract String getQueryDistritoDeLoja(int id);

        public abstract String getQueryConcelhoDeLoja(int id);

        public abstract String getQueryTodosConcelhos();

        public abstract String getQueryLojasPorNome(String nome);

        public abstract String getQueryLojasPorMorada(String morada);

        public abstract String getQueryLojasPorConcelho(int conc, int dist);

        //------------QUERYS BALCOES-----------------
        public abstract String getQueryBalcoesDeLoja(int id);

        public abstract String getQueryBalcaoPorID(int idloja, int identidade);

        public abstract String getQueryAddBalcao(Balcao b);

        public abstract String getQueryDeleteBalcao(Balcao b);

        public abstract String getQueryUpdateBalcao(Balcao b);

        //--------------QUERYS RELACOES------------------------
        public abstract String getQueryServicosDeBalcao(int idloja, int identidade);

        public abstract String getQueryServicoDeBalcaoPorID(int idloja, int identidade, int idservico);

        public abstract String getQueryAddServicoABalcao(RelacaoBalcaoServico r);

        public abstract String getQueryDeleteServicoDeBalcao(RelacaoBalcaoServico r);

        public abstract String getQueryUpdateServicoDeBalcao(RelacaoBalcaoServico r);
        
        //-------------LOG IN------------------------------------

        public abstract String getQueryLogIn(String nick);
    }
}