<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.LojaCidadao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Pesquisa de Lojas
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Pesquisa de Lojas</h2>

<% using (Html.BeginForm()) { %>
        <p>
            <label for="Nome">Pesquisa por Nome:</label>
            <%= Html.TextBox("Nome") %>
        </p>
        
        <p>
            <label for="Morada">Pesquisa por Morada:</label>
            <%= Html.TextBox("Morada") %>
        </p>
        
        <p>
            <label for="Concelho">Pesquisa por Concelho:</label>
            <%= Html.DropDownList("Concelho", ViewData["Concelhos"] as SelectList) %>
        </p>

        <p>
            <input type="submit" value="Pesquisa" />
        </p>
<% } %>

</asp:Content>
