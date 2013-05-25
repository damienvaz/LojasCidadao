<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.LojaCidadao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Detalhes
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Detalhes</h2>

<p>
    <%= Html.ActionLink("Ver Balcões de Loja", "Balcoes", new { id = Model.getId() })%>
    |
    <%= Html.ActionLink("Ver Loja no Mapa", "Mapa", new { id = Model.getId() })%>
</p>

<fieldset>
    <legend>Loja de Cidadão</legend>
    <p>
        Nome:
        <%= Html.Encode(Model.getNome()) %>
    </p>
    <p>
        Morada:
        <%= Html.Encode(Model.getMorada()) %>
    </p>
    <p>
        Código Postal:
        <%= Html.Encode(Model.getCod_postal()) %>
    </p>
    <p>
        Distrito:
        <%= Html.Encode(ViewData["Distrito"]) %>
    </p>
    <p>
        Concelho:
        <%= Html.Encode(ViewData["Concelho"]) %>
    </p>
    <p>
        Latitude:
        <%= Html.Encode(Model.getLatitude()) %>
    </p>
    <p>
        Longitude:
        <%= Html.Encode(Model.getLongitude()) %>
    </p>
    <p>
        Telefone:
        <%= Html.Encode(Model.getTelefone()) %>
    </p>
    <p>
        Estado:
        <%= Html.Encode(Model.isEstado()) %>
    </p>
</fieldset>
<p>
    <%= Html.ActionLink("Editar", "Edit", new { id = Model.getId() })%>
    |
    <%= Html.ActionLink("Apagar", "Delete", new { id = Model.getId() })%>
    |
    <%: Html.ActionLink("Voltar Atrás", "Index") %>
</p>

</asp:Content>
