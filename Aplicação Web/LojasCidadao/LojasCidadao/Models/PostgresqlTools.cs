using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Npgsql;

namespace LojasCidadao.Models
{
    public class PostgresqlTools : SQLTools
    {
        public PostgresqlTools(String server, String porta, String userID, String password, String database)
            : base(server, porta, userID, password, database)
        {
        }

        public override NpgsqlConnection getConnection()
        {
            String conString = "Server=" + base.server + ";Port=" + base.porta + ";User Id=" + base.userID + ";Password=" + base.password + ";Database=" + base.database + ";";
            NpgsqlConnection con = new NpgsqlConnection(conString);
            //con.Open();
            return con;
        }


        //-----------QUERYS ENTIDADES-----------------

        public override string getQueryEntidadePorID(int id)
        {
            string query = "SELECT * FROM public.entidades WHERE id_entidade=" + id + ";";
            return query;
        }

        public override string getQueryTodasEntidades()
        {
            string query = "SELECT * FROM public.entidades ORDER BY nome_entidade";
            return query;
        }

        public override string getQueryEntidadesPorNome(string nome)
        {
            string query = "SELECT * FROM public.entidades WHERE nome_entidade LIKE '%"+nome+"%' ORDER BY nome_entidade";
            return query;
        }

        public override string getQueryEntidadesPorSigla(string sigla)
        {
            string query = "SELECT * FROM public.entidades WHERE sigla_entidade LIKE '%" + sigla + "%' ORDER BY sigla_entidade";
            return query;
        }

        public override string getQueryAddEntidade(Entidade e)
        {
            string query = "INSERT INTO public.entidades (nome_entidade, sigla_entidade, morada_entidade, telefone_entidade, fax_entidade, email_entidade, url_entidade, site_entidade) VALUES ('" + e.getNome() + "', '"+e.getSigla()+"', '"+e.getMorada()+"', '"+e.getTelefone()+"', '"+e.getFax()+"', '"+e.getEmail()+"', '"+e.getUrl()+"', '"+e.getSite()+"')";
            return query;
        }

        public override string getQueryDeleteEntidade(int id)
        {
            string query = "DELETE FROM public.entidades WHERE id_entidade =" + id + ";";
            return query;
        }

        public override string getQueryUpdateEntidade(int id, Entidade novaEntidade)
        {
            string query = "UPDATE public.entidades SET nome_entidade='" + novaEntidade.getNome() + "', sigla_entidade='" + novaEntidade.getSigla() + "', morada_entidade='" + novaEntidade.getMorada() + "', telefone_entidade='" + novaEntidade.getTelefone() + "', fax_entidade='" + novaEntidade.getFax() + "', email_entidade='" + novaEntidade.getEmail() + "', url_entidade='" + novaEntidade.getUrl() + "', site_entidade='" + novaEntidade.getSite() + "' WHERE id_entidade=" + id + ";";
            return query;
        }

        //---------QUERYS SERVICOS------------
        public override string getQueryServicoPorID(int id)
        {
            string query = "SELECT * FROM public.servicos WHERE id_servico=" + id + ";";
            return query;
        }

        public override string getQueryTodosServicos()
        {
            string query = "SELECT * FROM public.servicos ORDER BY nome_servico";
            return query;
        }

        public override string getQueryServicosPorEntidade(int id)
        {
            string query = "SELECT * FROM public.servicos WHERE id_entidade=" + id + " ORDER BY nome_servico;";
            return query;
        }

        public override string getQueryServicosPorNome(String nome)
        {
            string query = "SELECT * FROM public.servicos WHERE nome_servico LIKE '%" + nome + "%' ORDER BY nome_servico";
            return query;
        }

        public override string getQueryServicosPorTipo(string tipo)
        {
            string query = "SELECT * FROM public.servicos WHERE tipo_servico LIKE '%" + tipo + "%' ORDER BY nome_servico";
            return query;
        }

        public override string getQueryAddServico(Servico s)
        {
            string query = "INSERT INTO public.servicos (id_entidade, nome_servico, descricao_servico, tipo_servico, url_servico, estado_servico) VALUES ('" + s.getEntidadeID() + "', '" + s.getNome() + "', '" + s.getDescricao() + "', '" + s.getTipo() + "', '" + s.getUrl() + "', '" + s.isAtivo() + "')";
            return query;
        }

        public override string getQueryDeleteServico(int id)
        {
            string query = "DELETE FROM public.servicos WHERE id_servico =" + id + ";";
            return query;
        }

        public override string getQueryUpdateServico(int id, Servico novo)
        {
            string query = "UPDATE public.servicos SET id_entidade=" + novo.getEntidadeID() + ", nome_servico='" + novo.getNome() + "', descricao_servico='" + novo.getDescricao() + "', tipo_servico='" + novo.getTipo() +"', url_servico='" + novo.getUrl() + "', estado_servico='" + novo.isAtivo() + "' WHERE id_servico=" + id + ";";
            return query;
        }

        public override string getQueryNomeEntidadeDeServico(int id)
        {
            string query = "SELECT e.nome_entidade FROM public.servicos as s, public.entidades as e WHERE s.id_servico=" + id + " AND s.id_entidade = e.id_entidade;";
            return query;
        }

        //--------------QUERYS LOJAS-------------------

        public override string getQueryLojaPorID(int id)
        {
            string query = "SELECT * FROM public.lojas_cidadao WHERE id_loja_cidacao=" + id + ";";
            return query;
        }

        public override string getQueryTodasLojas()
        {
            string query = "SELECT * FROM public.lojas_cidadao ORDER BY nome_loja_cidadao";
            return query;
        }

        public override string getQueryAddLoja(LojaCidadao l)
        {
            string query = "INSERT INTO public.lojas_cidadao (nome_loja_cidadao, codigo_postal_loja_cidadao, id_distrito_loja_cidadao, id_conselho_loja_cidadao, latitude_loja_cidadao, longitude_loja_cidadao, telefone_loja_cidadao, estado_loja_cidadao, morada_loja_cidadao) VALUES ('" + l.getNome() + "', '" + l.getCod_postal() + "', '" + l.getId_distrito() + "', '" + l.getId_conselho() + "', '" + l.getLatitude() + "', '" + l.getLongitude() + "', '" + l.getTelefone() + "', '" + l.isEstado() + "', '" + l.getMorada() + "')";
            return query;
        }

        public override string getQueryDeleteLoja(int id)
        {
            string query = "DELETE FROM public.lojas_cidadao WHERE id_loja_cidacao =" + id + ";";
            return query;
        }

        public override string getQuetyUpdateLoja(int id, LojaCidadao l)
        {
            string query = "UPDATE public.lojas_cidadao SET nome_loja_cidadao='" + l.getNome() + "', codigo_postal_loja_cidadao='" + l.getCod_postal() + "', id_distrito_loja_cidadao='" + l.getId_distrito() + "', id_conselho_loja_cidadao='" + l.getId_conselho() + "', latitude_loja_cidadao='" + l.getLatitude() + "', longitude_loja_cidadao='" + l.getLongitude() + "', telefone_loja_cidadao='" + l.getTelefone() + "', estado_loja_cidadao='" + l.isEstado() + "', morada_loja_cidadao='" + l.getMorada() + "' WHERE id_loja_cidacao=" + id + ";";
            return query;
        }

        public override string getQueryDistritoDeLoja(int id)
        {
            string query = "SELECT d.nome_distrito FROM public.lojas_cidadao as l, public.distritos as d WHERE l.id_loja_cidacao =" + id + " AND l.id_distrito_loja_cidadao = d.id_distrito;";
            return query;
        }

        public override string getQueryConcelhoDeLoja(int id)
        {
            string query = "SELECT c.nome_conselho FROM public.lojas_cidadao as l, public.concelhos as c WHERE l.id_loja_cidacao =" + id +" AND l.id_conselho_loja_cidadao = c.id_conselho AND l.id_distrito_loja_cidadao = c.id_distrito;";
            return query;
        }

        public override string getQueryTodosConcelhos()
        {
            string query = "SELECT * FROM public.concelhos ORDER BY nome_conselho;";
            return query;
        }

        public override string getQueryLojasPorNome(string nome)
        {
            string query = "SELECT * FROM public.lojas_cidadao WHERE nome_loja_cidadao LIKE '%"+nome+"%' ORDER BY nome_loja_cidadao;";
            return query;
        }

        public override string getQueryLojasPorMorada(string morada)
        {
            string query = "SELECT * FROM public.lojas_cidadao WHERE morada_loja_cidadao LIKE '%" + morada + "%' ORDER BY nome_loja_cidadao;";
            return query;
        }

        public override string getQueryLojasPorConcelho(int conc, int dist)
        {
            string query = "SELECT * FROM public.lojas_cidadao WHERE id_conselho_loja_cidadao ="+conc+" AND id_distrito_loja_cidadao="+dist+" ORDER BY nome_loja_cidadao;";
            return query;
        }

        //-----------QUERYS BALCOES------------------
        public override string getQueryBalcoesDeLoja(int id)
        {
            string query = "SELECT b.*,e.nome_entidade FROM public.balcoes as b, public.entidades as e WHERE b.id_loja_cidadao =" + id + " AND b.id_entidade = e.id_entidade;";
            return query;
        }

        public override string getQueryBalcaoPorID(int idloja, int identidade)
        {
            string query = "SELECT b.*,e.nome_entidade FROM public.balcoes as b, public.entidades as e WHERE b.id_loja_cidadao =" + idloja + " AND b.id_entidade =" + identidade + " AND b.id_entidade = e.id_entidade;";
            return query;
        }

        public override string getQueryAddBalcao(Balcao b)
        {
            string query = "INSERT INTO public.balcoes(id_loja_cidadao, id_entidade, estado_balcao) VALUES ("+b.getLojaID()+", "+b.getEntidadeID()+", '"+b.isEstado()+"');";
            return query;
        }

        public override string getQueryDeleteBalcao(Balcao b)
        {
            string query = "DELETE FROM public.balcoes WHERE id_loja_cidadao ="+b.getLojaID()+" AND id_entidade ="+b.getEntidadeID()+";";
            return query;
        }

        public override string getQueryUpdateBalcao(Balcao b)
        {
            string query = "UPDATE public.balcoes SET estado_balcao='" + b.isEstado() + "' WHERE id_loja_cidadao =" + b.getLojaID() + " AND id_entidade =" + b.getEntidadeID() + ";";
            return query;
        }

        //-----------------QUERYS DE RELACOES----------------------------
        public override string getQueryServicosDeBalcao(int idloja, int identidade)
        {
            string query = "SELECT r.*, s.nome_servico FROM public.relacoes_balcao_servico as r, public.servicos as s WHERE r.id_loja_cidadao ="+idloja+" AND r.id_entidade ="+identidade+" AND r.id_servico = s.id_servico;";
            return query;
        }

        public override string getQueryServicoDeBalcaoPorID(int idloja, int identidade, int idservico)
        {
            string query = "SELECT r.*, s.nome_servico FROM public.relacoes_balcao_servico as r, public.servicos as s WHERE r.id_loja_cidadao =" + idloja + " AND r.id_entidade =" + identidade + " AND r.id_servico =" + idservico + " AND r.id_servico = s.id_servico;";
            return query;
        }

        public override string getQueryAddServicoABalcao(RelacaoBalcaoServico r)
        {
            string query = "INSERT INTO public.relacoes_balcao_servico(id_loja_cidadao, id_entidade, id_servico, estado_servico_balcao) VALUES (" + r.getLojaID() + ", " + r.getEntidadeID() + ", " + r.getServicoID() + ", '" + r.isEstado() + "');";
            return query;
        }

        public override string getQueryDeleteServicoDeBalcao(RelacaoBalcaoServico r)
        {
            string query = "DELETE FROM public.relacoes_balcao_servico WHERE id_loja_cidadao ="+r.getLojaID()+" AND id_entidade ="+r.getEntidadeID()+" AND id_servico ="+r.getServicoID()+";";
            return query;
        }

        public override string getQueryUpdateServicoDeBalcao(RelacaoBalcaoServico r)
        {
            string query = "UPDATE public.relacoes_balcao_servico SET estado_servico_balcao='" + r.isEstado() + "' WHERE id_loja_cidadao =" + r.getLojaID() + " AND id_entidade =" + r.getEntidadeID() + " AND id_servico =" + r.getServicoID() + ";";
            return query;
        }

        //---------------LOG IN------------------------------

        public override string getQueryLogIn(string nick)
        {
            string query = "SELECT * FROM public.funcionarios WHERE codigo_funcionario ='" + nick + "';";
            return query;
        }
    }
}