<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Entidade>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Adicionar
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Adicionar Entidade</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>


<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Dados</legend>

        <p>
            <label for="Nome">Nome:</label>
            <%= Html.TextBox("Nome") %>
            <%= Html.ValidationMessage("Nome", "Nome incorreto") %>
        </p>
        <p>
            <label for="Sigla">Sigla:</label>
            <%= Html.TextBox("Sigla") %>
            <%= Html.ValidationMessage("Sigla", "Sigla incorreta") %>
        </p>
        <p>
            <label for="Morada">Morada:</label>
            <%= Html.TextBox("Morada") %>
            <%= Html.ValidationMessage("Morada", "Morada incorreta") %>
        </p>
        <p>
            <label for="Telefone">Telefone:</label>
            <%= Html.TextBox("Telefone") %>
            <%= Html.ValidationMessage("Telefone", "Telefone incorreto") %>
        </p>
        <p>
            <label for="Fax">Fax:</label>
            <%= Html.TextBox("Fax") %>
            <%= Html.ValidationMessage("Fax", "Fax incorreto") %>
        </p>
        <p>
            <label for="Email">Email:</label>
            <%= Html.TextBox("Email") %>
            <%= Html.ValidationMessage("Email", "Email incorreto") %>
        </p>
        <p>
            <label for="URL">URL:</label>
            <%= Html.TextBox("URL") %>
            <%= Html.ValidationMessage("URL", "URL incorreto") %>
        </p>
        <p>
            <label for="Site">Site:</label>
            <%= Html.TextBox("Site") %>
            <%= Html.ValidationMessage("Site", "Site incorreto") %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar à lista", "Index") %>
</div>

</asp:Content>
