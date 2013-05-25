<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<IEnumerable<LojasCidadao.Models.RelacaoBalcaoServico>>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Servicos do Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Serviços do Balcão</h2>

<p>
    <%: Html.ActionLink("Adicionar Serviço a Balcão", "CreateServicoBalcao",new { id = ViewData["Loja"].ToString()+"-"+ViewData["Entidade"].ToString() })%>
</p>
<table>
    <thead>
        <tr>
            <th>Serviço</th>
            <th>Tipo</th>
            <th>Estado</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <% foreach (var item in Model) { %>
        <tr>
            <td><%= Html.Encode(item.getNomeServico()) %></td>
            <td><%= Html.Encode(item.getTipoServico()) %></td>
            <td><%= Html.Encode(item.isEstado()) %></td>
            <td><%= Html.ActionLink("Editar Serviço", "EditServicoBalcao", new { id = item.getLojaID().ToString()+"-"+item.getEntidadeID().ToString()+"-"+item.getServicoID().ToString() })%></td>
            <td><%= Html.ActionLink("Apagar Serviço", "DeleteServicoBalcao", new { id = item.getLojaID().ToString()+"-"+item.getEntidadeID().ToString()+"-"+item.getServicoID().ToString() })%></td>
        </tr>
    <% } %>
</table>

<p>
    <%: Html.ActionLink("Voltar atrás", "Balcoes", new { id = ViewData["Loja"] })%>
</p>

</asp:Content>
