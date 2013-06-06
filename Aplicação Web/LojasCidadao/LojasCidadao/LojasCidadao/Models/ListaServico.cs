using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class ListaServico
    {
        NpgsqlConnection con;

        public ListaServico(NpgsqlConnection con)
        {
            this.con = con;
        }

        public ListaServico()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);
        }

        public List<Servico> getTodosServicos()
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryTodosServicos());
            List<Servico> lista = new List<Servico>();
            while (reader.Read())
            {
                Servico s = servicoFromRS(reader);
                lista.Add(s);
            }
            reader.Close();
            return lista;
        }

        public Servico getServicoPorID(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicoPorID(id));
            Servico s = new Servico();
            if (reader.Read())
            {
                s = servicoFromRS(reader);
                reader.Close();
                return s;
            }
            else return null;
        }

        public List<Servico> getServicosPorEntidade(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicosPorEntidade(id));
            List<Servico> lista = new List<Servico>();
            while (reader.Read())
            {
                Servico s = servicoFromRS(reader);
                lista.Add(s);
            }
            reader.Close();
            return lista;
        }

        public List<Servico> getServicosPorNome(String nome)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicosPorNome(nome));
            List<Servico> lista = new List<Servico>();
            while (reader.Read())
            {
                Servico s = servicoFromRS(reader);
                lista.Add(s);
            }
            reader.Close();
            return lista;
        }

        public List<Servico> getServicosPorTipo(String tipo)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicosPorTipo(tipo));
            List<Servico> lista = new List<Servico>();
            while (reader.Read())
            {
                Servico s = servicoFromRS(reader);
                lista.Add(s);
            }
            reader.Close();
            return lista;
        }

        public String nomeEntidade(int id)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryNomeEntidadeDeServico(id));
            String nome = "";
            if (reader.Read())
            {
                nome = reader.GetValue(0).ToString();
                reader.Close();
            }
            return nome;
        }

        public void addServico(Servico s)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryAddServico(s));
            reader.Close();
        }

        public void deleteServico(Servico s)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDeleteServico(s.getID()));
            reader.Close();
        }

        public void updateServico(Servico s)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryUpdateServico(s.getID(), s));
            reader.Close();
        }

        public List<String> listaNomesServicosPorEntidade(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicosPorEntidade(id));
            List<String> lista = new List<String>();
            lista.Add("");
            while (reader.Read())
            {
                String e = servicoAsString(reader);
                lista.Add(e);
            }
            reader.Close();
            return lista;
        }

        private String servicoAsString(NpgsqlDataReader reader)
        {
            String s = reader.GetInt32(0).ToString() + "-" + reader.GetValue(2).ToString();
            return s;
        }

        private Servico servicoFromRS(NpgsqlDataReader reader)
        {
            Servico s = new Servico(reader.GetInt32(0), reader.GetInt32(1), reader.GetValue(2).ToString(), reader.GetValue(3).ToString(), reader.GetValue(4).ToString(), reader.GetValue(5).ToString(), reader.GetBoolean(6));
            return s;
        }
    }
}