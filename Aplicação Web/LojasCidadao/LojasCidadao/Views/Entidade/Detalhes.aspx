<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Entidade>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Detalhes
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Detalhes</h2>

<fieldset>
    <legend><%= Html.Encode(Model.getSigla()) %></legend>
    <p>
        Morada:
        <%= Html.Encode(Model.getMorada()) %>
    </p>
    <p>
        Telefone:
        <%= Html.Encode(Model.getTelefone()) %>
    </p>
    <p>
        Fax:
        <%= Html.Encode(Model.getFax()) %>
    </p>
    <p>
        Email:
        <%= Html.Encode(Model.getEmail()) %>
    </p>
    <p>
        URL:
        <%= Html.Encode(Model.getUrl()) %>
    </p>
    <p>
        Site:
        <%= Html.Encode(Model.getSite()) %>
    </p>
</fieldset>
<p>
    <%= Html.ActionLink("Editar", "Edit", new { id = Model.getID() })%>
    |
    <%= Html.ActionLink("Apagar", "Delete", new { id = Model.getID() })%>
    |
    <%: Html.ActionLink("Voltar Atrás", "Index") %>
</p>

</asp:Content>
