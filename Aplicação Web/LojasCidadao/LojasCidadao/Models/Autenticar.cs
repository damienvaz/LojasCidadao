using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace LojasCidadao.Models
{
    public class Autenticar
    {
        private NpgsqlConnection con;

        public Autenticar()
        {
            DataBaseUser dbu = new DataBaseUser(1, "webgis.di.uminho.pt", "5432", "g05", "20130423", "lojas", "PostgreSQL");
            con = DataBaseTools.getConnection(dbu);
        }

        public Autenticar(NpgsqlConnection con)
        {
            this.con = con;
        }

        public Funcionario getFuncionario(String nick)
        {
            NpgsqlDataReader reader = DataBaseTools.getResultFrom(con, DataBaseTools.getQueryLogIn(nick));
            Funcionario f = new Funcionario();
            if (reader.Read())
            {
                f = funcionarioFromRS(reader);
                reader.Close();
                return f;
            }
            else return null;
        }

        private Funcionario funcionarioFromRS(NpgsqlDataReader reader)
        {
            Funcionario f = new Funcionario(reader.GetValue(1).ToString(), reader.GetValue(2).ToString(), reader.GetValue(3).ToString());
            return f;
        }
    }
}