using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class ListaBalcao
    {
        private NpgsqlConnection con;

        public ListaBalcao(NpgsqlConnection con)
        {
            this.con = con;
        }

        public ListaBalcao()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);
        }

        public List<Balcao> getBalcoesDeLoja(int id)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryBalcoesDeLoja(id));
            List<Balcao> lista = new List<Balcao>();
            while (reader.Read())
            {
                Balcao b = balcaoFromRS(reader);
                lista.Add(b);
            }
            reader.Close();
            return lista;
        }

        public Balcao getBalcaoPorID(int idloja, int identidade)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryBalcaoPorID(idloja, identidade));
            Balcao b = new Balcao();
            if (reader.Read())
            {
                b = balcaoFromRS(reader);
                reader.Close();
                return b;
            }
            else return null;
        }

        public void addBalcao(Balcao b)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryAddBalcao(b));
            reader.Close();
        }

        public void deleteBalcao(Balcao b)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDeleteBalcao(b));
            reader.Close();
        }

        public void updateBalcao(Balcao b)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryUpdateBalcao(b));
            reader.Close();
        }

        public List<RelacaoBalcaoServico> getServicosDeBalcao(int idloja, int identidade)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicosDeBalcao(idloja,identidade));
            List<RelacaoBalcaoServico> lista = new List<RelacaoBalcaoServico>();
            while (reader.Read())
            {
                RelacaoBalcaoServico r = relacaoFromRS(reader);
                lista.Add(r);
            }
            reader.Close();
            return lista;
        }

        public RelacaoBalcaoServico getServicoDeBalcaoPorID(int idloja, int identidade, int idservico)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryServicoDeBalcaoPorID(idloja, identidade,idservico));
            RelacaoBalcaoServico r = new RelacaoBalcaoServico();
            if (reader.Read())
            {
                r = relacaoFromRS(reader);
                reader.Close();
                return r;
            }
            else return null;
        }

        public void addServicoABalcao(RelacaoBalcaoServico r)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryAddServicoABalcao(r));
            reader.Close();
        }

        public void deleteServicoDeBalcao(RelacaoBalcaoServico r)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryDeleteServicoDeBalcao(r));
            reader.Close();
        }

        public void updateServicoDeBalcao(RelacaoBalcaoServico r)
        {
            //con.Open();
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryUpdateServicoDeBalcao(r));
            reader.Close();
        }

        private Balcao balcaoFromRS(NpgsqlDataReader reader)
        {
            Balcao b = new Balcao(reader.GetInt32(0), reader.GetInt32(1), reader.GetBoolean(2), reader.GetValue(3).ToString());
            return b;
        }

        private RelacaoBalcaoServico relacaoFromRS(NpgsqlDataReader reader)
        {
            RelacaoBalcaoServico r = new RelacaoBalcaoServico(reader.GetInt32(0), reader.GetInt32(1), reader.GetInt32(2), reader.GetBoolean(3), reader.GetValue(4).ToString(), reader.GetValue(5).ToString());
            return r;
        }
    }
}