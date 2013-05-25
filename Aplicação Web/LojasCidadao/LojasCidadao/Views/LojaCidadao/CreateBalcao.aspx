<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Balcao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Adicionar Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Adicionar Balcão</h2>

<script src="<%: Url.Content("~/Scripts/jquery.validate.min.js") %>" type="text/javascript"></script>
<script src="<%: Url.Content("~/Scripts/jquery.validate.unobtrusive.min.js") %>" type="text/javascript"></script>

<% using (Html.BeginForm()) { %>
    <fieldset>
        <legend>Criar Balcão</legend>

         <p>
            <label for="Loja">Loja ID:</label>
            <%= Html.TextBox("Loja", ViewData["LojaID"], new { @readonly="readonly" })%>
            <%= Html.ValidationMessage("Loja", "Loja incorreta") %>
        </p>
        <p>
            <label for="Entidade">Entidade:</label>
            <%= Html.DropDownList("Entidade", ViewData["Entidades"] as SelectList) %>
            <%= Html.ValidationMessage("Entidade", "Entidade incorreta") %>
        </p>
        <p>
            <label for="Estado">Estado (CheckBox selecionada para ativar balcão):</label>
            <%= Html.CheckBox("Estado", Model.isEstado()) %>
        </p>
        <p>
            <input type="submit" value="Guardar" />
        </p>
    </fieldset>
<% } %>

<div>
    <%: Html.ActionLink("Voltar à Lista", "Balcoes", new { id = ViewData["LojaID"] })%>
</div>

</asp:Content>
