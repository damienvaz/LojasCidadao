<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.Balcao>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Apagar Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>
    Confirmação de Eliminação
</h2>

<div>
    <p>Confirma que quer eliminar este balcão?</p>
</div>

<% using (Html.BeginForm()) { %>
    <input name="confirmButton" type="submit" value="Apagar" />
<% } %>

<%: Html.ActionLink("Voltar atrás", "Balcoes", new { id = ViewData["Loja"] })%>

</asp:Content>
