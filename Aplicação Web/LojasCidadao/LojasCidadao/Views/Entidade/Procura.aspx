<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Entidade>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Pesquisa
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Pesquisa de Entidades</h2>

<% using (Html.BeginForm()) { %>
        <p>
            <label for="Nome">Pesquisa por Nome:</label>
            <%= Html.TextBox("Nome") %>
        </p>
        
        <p>
            <label for="Sigla">pesquisa por Sigla:</label>
            <%= Html.TextBox("Sigla") %>
        </p>

        <p>
            <input type="submit" value="Pesquisa" />
        </p>
<% } %>

</asp:Content>
