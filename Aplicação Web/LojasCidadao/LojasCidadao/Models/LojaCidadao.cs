using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LojasCidadao.Models
{
    public class LojaCidadao
    {
        private int id;
        private String nome;
        private String morada;
        private String cod_postal;
        private int id_distrito;
        private int id_conselho;
        private String latitude;
        private String longitude;
        private String telefone;
        private bool estado;

        public LojaCidadao()
        {
            this.id = 0;
            this.nome = "";
            this.morada = "";
            this.cod_postal = "";
            this.id_distrito = 0;
            this.id_conselho = 0;
            this.latitude = "";
            this.longitude = "";
            this.telefone = "";
            this.estado = true;
        }

        public LojaCidadao(int id, String nome, String cod_postal, int id_distrito, int id_conselho, String latitude, String longitude, String telefone, bool estado, String morada)
        {
            this.id = id;
            this.nome = nome;
            this.morada = morada;
            this.cod_postal = cod_postal;
            this.id_distrito = id_distrito;
            this.id_conselho = id_conselho;
            this.latitude = latitude;
            this.longitude = longitude;
            this.telefone = telefone;
            this.estado = estado;
        }

        public String getCod_postal()
        {
            return cod_postal;
        }

        public void setCod_postal(String cod_postal)
        {
            this.cod_postal = cod_postal;
        }

        public bool isEstado()
        {
            return estado;
        }

        public void setEstado(bool estado)
        {
            this.estado = estado;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getId_conselho()
        {
            return id_conselho;
        }

        public void setId_conselho(int id_conselho)
        {
            this.id_conselho = id_conselho;
        }

        public int getId_distrito()
        {
            return id_distrito;
        }

        public void setId_distrito(int id_distrito)
        {
            this.id_distrito = id_distrito;
        }

        public String getLatitude()
        {
            return latitude;
        }

        public void setLatitude(String latitude)
        {
            this.latitude = latitude;
        }

        public String getLongitude()
        {
            return longitude;
        }

        public void setLongitude(String longitude)
        {
            this.longitude = longitude;
        }

        public String getMorada()
        {
            return morada;
        }

        public void setMorada(String morada)
        {
            this.morada = morada;
        }

        public String getNome()
        {
            return nome;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public String getTelefone()
        {
            return telefone;
        }

        public void setTelefone(String telefone)
        {
            this.telefone = telefone;
        }

        public IEnumerable<RuleViolation> GetRuleViolations()
        {
            if (String.IsNullOrEmpty(nome))
                yield return new RuleViolation("Nome necessário", "Nome");
            if (String.IsNullOrEmpty(morada))
                yield return new RuleViolation("Morada necessária", "Morada");
            if (String.IsNullOrEmpty(cod_postal))
                yield return new RuleViolation("Código Postal necessário", "CodPostal");
            if (id_distrito == 0)
                yield return new RuleViolation("Distrito necessário", "Distrito");
            if (id_conselho == 0)
                yield return new RuleViolation("Concelho necessário", "Concelho");
            if (String.IsNullOrEmpty(latitude))
                yield return new RuleViolation("Latitude necessária", "Latitude");
            if (String.IsNullOrEmpty(longitude))
                yield return new RuleViolation("Longitude necessária", "Longitude");
            yield break;
        }

        public bool IsValid
        {
            get { return (GetRuleViolations().Count() == 0); }
        }
    }
}