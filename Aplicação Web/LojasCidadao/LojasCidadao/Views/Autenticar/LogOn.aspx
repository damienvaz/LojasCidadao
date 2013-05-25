<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<dynamic>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
    Log On
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

<h2>Log On</h2>

<% using (Html.BeginForm()) { %>
    <%: Html.ValidationSummary(true, "Log in não foi bem sucedido. Corrija os erros e tente novamente:") %>
        <div>
            <fieldset>
                <legend>Informações da conta</legend>
                
                <p>
                    <label for="Nick">Nick:</label>
                    <%= Html.TextBox("Nick") %>
                </p>
                <p>
                    <label for="Pass">Password:</label>
                    <%= Html.Password("Password") %>
                </p>
                <p>
                    <label for="RememberMe">Remember Me?</label>
                    <%= Html.CheckBox("RememberMe", false) %>
                </p>
                
                <p>
                    <input type="submit" value="Log On" />
                </p>
            </fieldset>
        </div>
    <% } %>

</asp:Content>
