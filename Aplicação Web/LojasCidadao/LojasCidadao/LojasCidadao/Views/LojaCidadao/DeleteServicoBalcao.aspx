<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<LojasCidadao.Models.RelacaoBalcaoServico>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Apagar Serviço de Balcão
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>
    Confirmação de Eliminação
</h2>

<div>
    <p>Confirma que quer eliminar este serviço deste balcão?</p>
</div>

<% using (Html.BeginForm()) { %>
    <input name="confirmButton" type="submit" value="Apagar" />
<% } %>

<%: Html.ActionLink("Voltar atrás", "ServicosBalcao", new { id = ViewData["Loja"].ToString()+"-"+ViewData["Entidade"].ToString() })%>

</asp:Content>
