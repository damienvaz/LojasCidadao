<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Resultados da Pesquisa
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Resultados da Pesquisa</h2>

<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Sigla</th>
            <th></th>
        </tr>
    </thead>
    <% foreach (var item in Model) { %>
        <tr>
            <td><%= Html.Encode(item.getNome()) %></td>
            <td><%= Html.Encode(item.getSigla()) %></td>
            <td><%= Html.ActionLink("Detalhes", "Detalhes", new { id = item.getID() })%></td>
        </tr>
    <% } %>
</table>

<div>
    <%: Html.ActionLink("Voltar à Pesquisa", "Procura") %>
</div>

</asp:Content>
