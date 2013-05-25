<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Servico>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Editar
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Editar</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Editar Serviço</legend>

        <p>
            <label for="Entidade">Entidade:</label>
            <%= Html.DropDownList("Entidade", ViewData["Entidades"] as SelectList) %>
            <%= Html.ValidationMessage("Entidade", "Entidade incorreta") %>
        </p>
        <p>
            <label for="Nome">Nome:</label>
            <%= Html.TextBox("Nome", Model.getNome()) %>
            <%= Html.ValidationMessage("Nome", "Nome incorreto") %>
        </p>
        <p>
            <label for="Descrição">Descrição:</label>
            <%= Html.TextBox("Descrição", Model.getDescricao()) %>
            <%= Html.ValidationMessage("Descrição", "Descrição incorreto") %>
        </p>
        <p>
            <label for="Tipo">Tipo:</label>
            <%= Html.TextBox("Tipo", Model.getTipo()) %>
            <%= Html.ValidationMessage("Tipo", "Tipo incorreto") %>
        </p>
        <p>
            <label for="URL">URL:</label>
            <%= Html.TextBox("URL", Model.getUrl()) %>
            <%= Html.ValidationMessage("URL", "URL incorreto") %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar Atrás", "Detalhes", new { id = Model.getID() })%>
</div>

</asp:Content>
