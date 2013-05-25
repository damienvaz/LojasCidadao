<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Servico>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Detalhes
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Detalhes</h2>

<fieldset>
    <legend>Serviço</legend>
    <p>
        Entidade:
        <%= Html.Encode(ViewData["Entidade"]) %>
    </p>
    <p>
        Nome:
        <%= Html.Encode(Model.getNome()) %>
    </p>
    <p>
        Descrição:
        <%= Html.Encode(Model.getDescricao()) %>
    </p>
    <p>
        Tipo:
        <%= Html.Encode(Model.getTipo()) %>
    </p>
    <p>
        URL:
        <%= Html.Encode(Model.getUrl()) %>
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
