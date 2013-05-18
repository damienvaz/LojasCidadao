using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace LojasCidadao.Models
{
    public class DataBaseUser
    {
        private int tipoBD;
        private String server;
        private String porta;
        private String userID;
        private String password;
        private String database;
        private String nome;

        public DataBaseUser(int tipoBD, String server, String porta, String userID, String password, String database, String nome)
        {
            this.tipoBD = tipoBD;
            this.server = server;
            this.porta = porta;
            this.userID = userID;
            this.password = password;
            this.database = database;
            this.nome = nome;
        }

        public int getTipoBD()
        {
            return tipoBD;
        }

        public String getServer()
        {
            return server;
        }

        public String getPorta()
        {
            return porta;
        }

        public String getUserID()
        {
            return userID;
        }

        public String getPassword()
        {
            return password;
        }

        public String getDatabase()
        {
            return database;
        }

        public String getNome()
        {
            return nome;
        }
    }
}