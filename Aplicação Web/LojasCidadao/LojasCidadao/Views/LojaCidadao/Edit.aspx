<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.LojaCidadao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Editar
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Editar</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Editar Loja</legend>

        <p>
            <label for="Nome">Nome:</label>
            <%= Html.TextBox("Nome", Model.getNome()) %>
            <%= Html.ValidationMessage("Nome", "Nome incorreto") %>
        </p>
        <p>
            <label for="Morada">Morada:</label>
            <%= Html.TextBox("Morada", Model.getMorada()) %>
            <%= Html.ValidationMessage("Morada", "Morada incorreta") %>
        </p>
        <p>
            <label for="CodPostal">Código Postal:</label>
            <%= Html.TextBox("CodPostal", Model.getCod_postal()) %>
            <%= Html.ValidationMessage("CodPostal", "Código Postal incorreto") %>
        </p>
        <p>
            <label for="Concelho">Concelho (Ao escolher um concelho o distrito é automaticamente escolhido):</label>
            <%= Html.DropDownList("Concelho", ViewData["Concelhos"] as SelectList) %>
            <%= Html.ValidationMessage("Concelho", "Concelho incorreto") %>
        </p>
        <p>
            <label for="Latitude">Latitude:</label>
            <%= Html.TextBox("Latitude", Model.getLatitude()) %>
            <%= Html.ValidationMessage("Latitude", "Latitude incorreta") %>
        </p>
        <p>
            <label for="Longitude">Longitude:</label>
            <%= Html.TextBox("Longitude", Model.getLongitude()) %>
            <%= Html.ValidationMessage("Longitude", "Longitude incorreta") %>
        </p>
        <p>
            <label for="Telefone">Telefone:</label>
            <%= Html.TextBox("Telefone", Model.getTelefone()) %>
            <%= Html.ValidationMessage("Telefone", "Telefone incorreto") %>
        </p>
        <p>
            <label for="Estado">Estado (CheckBox selecionada para ativar loja):</label>
            <%= Html.CheckBox("Estado", Model.isEstado()) %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar Atrás", "Detalhes", new { id = Model.getId() })%>
</div>

</asp:Content>
