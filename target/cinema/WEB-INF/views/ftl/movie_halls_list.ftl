<html>
    <head>
        <title>${listOfMovieHallsTitleKey}</title>
    </head>
    <body>
        <h1>${listOfMovieHallsTitleKey}</h1>
        <a href=${adminPanelNavigationPathKey}>${backToAdminPanelKey}</a>
        <form method="post">
            <ol type="1">
                <#list movieHallsListModelKey as movieHall>
                    <li>
                        ${movieHall.id} ${movieHall.numberOfSeats} <input type="checkbox" name="roomId" value="${movieHall.id}">
                    </li>
                </#list>
            </ol>
            <input type="submit" value="Delete selected halls">
        </form>
    </body>
</html>