<html>
<head>
<title>${movieSessionListTitleKey}</title>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>
</head>
    <body>
        <h1>${movieSessionListTitleKey}</h1>
        <a href=${adminPanelNavigationPathKey}>${backToAdminPanelKey}</a>
        <form method="post">
            <table>
            <tr>
                <th>Time</th>
                <th>Date</th>
                <th>Movie title</th>
                <th>Movie hall</th>
                <th>Delete</th>
            </tr>
            <#list movieSessionListModelKey as movieSession>
                <tr>
                    <td>${movieSession.time}</td>
                    <td>${movieSession.date}</td>
                    <td>${movieSession.movieTitle}</td>
                    <td>${movieSession.movieHallName}</td>
                    <td><input type="checkbox" name="movieSessionIdList" value="${movieSession.id}"></td>
                </tr>
            </#list>
            </table>
            <input type="submit" value="Delete selected movie sessions">
        </form>
    </body>
</html>