<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<IEnumerable<LojasCidadao.Models.LojaCidadao>>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Todas as Lojas</h2>

<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Estado</th>
            <th></th>
        </tr>
    </thead>
    <% foreach (var item in Model) { %>
        <tr>
            <td><%= Html.Encode(item.getNome()) %></td>
            <td><%= Html.Encode(item.isEstado()) %></td>
            <td><%= Html.ActionLink("Detalhes", "Detalhes", new { id = item.getId() })%></td>
        </tr>
    <% } %>
</table>

</asp:Content>
