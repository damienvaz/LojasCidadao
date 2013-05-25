<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Apagar
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>
    Confirmação de Eliminação
</h2>

<div>
    <p>Confirma que quer eliminar:
    <i> <%=Html.Encode(Model.getNome()) %>? </i> </p>
</div>

<% using (Html.BeginForm()) { %>
    <input name="confirmButton" type="submit" value="Apagar" />
<% } %>

<%: Html.ActionLink("Voltar Atrás", "Detalhes", new { id = Model.getID() })%>

</asp:Content>
