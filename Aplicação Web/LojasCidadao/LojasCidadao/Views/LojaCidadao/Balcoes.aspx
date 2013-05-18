<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<IEnumerable<LojasCidadao.Models.Balcao>>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Balcões
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Balcões</h2>

<p>
    <%: Html.ActionLink("Adicionar Balcão", "CreateBalcao", new { id = ViewData["Loja"] })%>
</p>
<table>
    <thead>
        <tr>
            <th>Entidade</th>
            <th>Estado</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <% foreach (var item in Model) { %>
        <tr>
            <td><%= Html.Encode(item.getNomeEntidade()) %></td>
            <td><%= Html.Encode(item.isEstado()) %></td>
            <td><%= Html.ActionLink("Editar Balcão", "EditBalcao", new { id = item.getLojaID().ToString()+"-"+item.getEntidadeID().ToString() })%></td>
            <td><%= Html.ActionLink("Apagar Balcão", "DeleteBalcao", new { id = item.getLojaID().ToString()+"-"+item.getEntidadeID().ToString() })%></td>
            <td><%= Html.ActionLink("Ver Serviços do Balcão", "ServicosBalcao", new { id = item.getLojaID().ToString()+"-"+item.getEntidadeID().ToString() })%></td>
        </tr>
    <% } %>
</table>

<p>
    <%: Html.ActionLink("Voltar atrás", "Detalhes", new { id = ViewData["Loja"] })%>
</p>

</asp:Content>
