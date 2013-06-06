<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Todas as Entidades</h2>

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

</asp:Content>
