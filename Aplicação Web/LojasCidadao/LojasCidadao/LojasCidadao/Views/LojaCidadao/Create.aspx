<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.LojaCidadao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Adicionar Loja
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Adicionar Loja</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Criar Loja</legend>

        <p>
            <label for="Nome">Nome:</label>
            <%= Html.TextBox("Nome") %>
            <%= Html.ValidationMessage("Nome", "Nome incorreto") %>
        </p>
        <p>
            <label for="Morada">Morada:</label>
            <%= Html.TextBox("Morada") %>
            <%= Html.ValidationMessage("Morada", "Morada incorreta") %>
        </p>
        <p>
            <label for="CodPostal">Código Postal:</label>
            <%= Html.TextBox("CodPostal") %>
            <%= Html.ValidationMessage("CodPostal", "Código Postal incorreto") %>
        </p>
        <p>
            <label for="Concelho">Concelho (Ao escolher um concelho o distrito é automaticamente escolhido):</label>
            <%= Html.DropDownList("Concelho", ViewData["Concelhos"] as SelectList) %>
            <%= Html.ValidationMessage("Concelho", "Concelho incorreto") %>
        </p>
        <p>
            <label for="Latitude">Latitude:</label>
            <%= Html.TextBox("Latitude") %>
            <%= Html.ValidationMessage("Latitude", "Latitude incorreta") %>
        </p>
        <p>
            <label for="Longitude">Longitude:</label>
            <%= Html.TextBox("Longitude") %>
            <%= Html.ValidationMessage("Longitude", "Longitude incorreta") %>
        </p>
        <p>
            <label for="Telefone">Telefone:</label>
            <%= Html.TextBox("Telefone") %>
            <%= Html.ValidationMessage("Telefone", "Telefone incorreto") %>
        </p>
        <p>
            <label for="Estado">Estado (CheckBox selecionada para ativar loja):</label>
            <%= Html.CheckBox("Estado", true) %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar à Lista", "Index") %>
</div>

</asp:Content>
