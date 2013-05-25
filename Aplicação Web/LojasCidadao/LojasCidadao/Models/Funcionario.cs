using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class Funcionario
    {
        private String nome;
        private String nick;
        private String password;

        public Funcionario()
        {
            this.nome = "";
            this.nick = "";
            this.password = "";
        }

        public Funcionario(String nome, String nick, String password)
        {
            this.nome = nome;
            this.nick = nick;
            this.password = password;
        }

        public String getNome()
        {
            return nome;
        }

        public String getNick()
        {
            return nick;
        }

        public String getPassword()
        {
            return password;
        }
    }
}