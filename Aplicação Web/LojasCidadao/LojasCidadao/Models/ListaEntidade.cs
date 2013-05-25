using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class ListaEntidade
    {
        private NpgsqlConnection con;

        public ListaEntidade(NpgsqlConnection con)
        {
            this.con = con;
        }

        public ListaEntidade()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);
        }

        public List<Entidade> getTodasEntidades()
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryTodasEntidade());
            List<Entidade> lista = new List<Entidade>();
            while (reader.Read())
            {
                Entidade e = entidadeFromRS(reader);
                lista.Add(e);
            }
            reader.Close();
            return lista;
        }

        public Entidade getEntidadePorID(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryEntidadePorID(id));
            Entidade e = new Entidade();
            if (reader.Read())
            {
                e = entidadeFromRS(reader);
                reader.Close();
                return e;
            }
            else return null;
        }

        public List<Entidade> getEntidadesPorNome(String nome)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryEntidadesPorNome(nome));
            List<Entidade> lista = new List<Entidade>();
            while (reader.Read())
            {
                Entidade e = entidadeFromRS(reader);
                lista.Add(e);
            }
            reader.Close();
            return lista;
        }

        public List<Entidade> getEntidadesPorSigla(String sigla)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryEntidadesPorSigla(sigla));
            List<Entidade> lista = new List<Entidade>();
            while (reader.Read())
            {
                Entidade e = entidadeFromRS(reader);
                lista.Add(e);
            }
            reader.Close();
            return lista;
        }

        public void addEntidade(Entidade e)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryAddEntidade(e));
            reader.Close();
        }

        public void deleteEntidade(Entidade e)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDeleteEntidade(e.getID()));
            reader.Close();
        }

        public void updateEntidade(Entidade e)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryUpdateEntidade(e.getID(), e));
            reader.Close();
        }

        public List<String> listaEntidadesAsStrings()
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryTodasEntidade());
            List<String> lista = new List<String>();
            lista.Add("");
            while (reader.Read())
            {
                String e = entidadeAsString(reader);
                lista.Add(e);
            }
            reader.Close();
            return lista;
        }

        private String entidadeAsString(NpgsqlDataReader reader)
        {
            String entidade = reader.GetInt32(0).ToString() +"-"+ reader.GetValue(1).ToString();
            return entidade;
        }

        private Entidade entidadeFromRS(NpgsqlDataReader reader)
        {
            Entidade t = new Entidade(reader.GetInt32(0), reader.GetValue(1).ToString(), reader.GetValue(2).ToString(), reader.GetValue(3).ToString(), reader.GetValue(4).ToString(), reader.GetValue(5).ToString(), reader.GetValue(6).ToString(), reader.GetValue(7).ToString(), reader.GetValue(8).ToString());
            return t;
        }
    }
}