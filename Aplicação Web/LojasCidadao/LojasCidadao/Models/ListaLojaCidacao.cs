using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class ListaLojaCidacao
    {
        private NpgsqlConnection con;

        public ListaLojaCidacao(NpgsqlConnection con)
        {
            this.con = con;
        }

        public ListaLojaCidacao()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);
        }

        public List<LojaCidadao> getTodasLojas()
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryTodasLojas());
            List<LojaCidadao> lista = new List<LojaCidadao>();
            while (reader.Read())
            {
                LojaCidadao l = lojaFromRS(reader);
                lista.Add(l);
            }
            reader.Close();
            return lista;
        }

        public LojaCidadao getLojaPorID(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryLojaPorID(id));
            LojaCidadao l = new LojaCidadao();
            if (reader.Read())
            {
                l = lojaFromRS(reader);
                reader.Close();
                return l;
            }
            else return null;
        }

        public String nomeDistrito(int id)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDistritoDeLoja(id));
            String nome = "";
            if (reader.Read())
            {
                nome = reader.GetValue(0).ToString();
                reader.Close();
            }
            return nome;
        }

        public String nomeConcelho(int id)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryConcelhoDeLoja(id));
            String nome = "";
            if (reader.Read())
            {
                nome = reader.GetValue(0).ToString();
                reader.Close();
            }
            return nome;
        }

        public List<String> getTodosConcelhos()
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryTodosConcelhos());
            List<String> lista = new List<String>();
            lista.Add("");
            while (reader.Read())
            {
                String l = concelhoAsString(reader);
                lista.Add(l);
            }
            reader.Close();
            return lista;
        }

        public List<LojaCidadao> getLojasPorNome(String nome)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryLojasPorNome(nome));
            List<LojaCidadao> lista = new List<LojaCidadao>();
            while (reader.Read())
            {
                LojaCidadao l = lojaFromRS(reader);
                lista.Add(l);
            }
            reader.Close();
            return lista;
        }

        public List<LojaCidadao> getLojasPorMorada(String morada)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryLojasPorMorada(morada));
            List<LojaCidadao> lista = new List<LojaCidadao>();
            while (reader.Read())
            {
                LojaCidadao l = lojaFromRS(reader);
                lista.Add(l);
            }
            reader.Close();
            return lista;
        }

        public List<LojaCidadao> getLojasPorConcelho(int concelho, int distrito)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryLojasPorConcelho(concelho,distrito));
            List<LojaCidadao> lista = new List<LojaCidadao>();
            while (reader.Read())
            {
                LojaCidadao l = lojaFromRS(reader);
                lista.Add(l);
            }
            reader.Close();
            return lista;
        }

        public void addLoja(LojaCidadao l)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryAddLoja(l));
            reader.Close();
        }

        public void deleteLoja(LojaCidadao l)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDeleteLoja(l.getId()));
            reader.Close();
        }

        public void updateLoja(LojaCidadao l)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryUpdateLoja(l.getId(), l));
            reader.Close();
        }

        private String concelhoAsString(NpgsqlDataReader reader)
        {
            String concelho = reader.GetInt32(0).ToString() + "-" + reader.GetInt32(1).ToString() + "-" + reader.GetValue(2).ToString();
            return concelho;
        }

        private LojaCidadao lojaFromRS(NpgsqlDataReader reader)
        {
            LojaCidadao l = new LojaCidadao(reader.GetInt32(0), reader.GetValue(1).ToString(), reader.GetValue(2).ToString(), reader.GetInt32(3), reader.GetInt32(4), reader.GetValue(5).ToString(), reader.GetValue(6).ToString(), reader.GetValue(7).ToString(), reader.GetBoolean(8), reader.GetValue(9).ToString());
            return l;
        }
    }
}