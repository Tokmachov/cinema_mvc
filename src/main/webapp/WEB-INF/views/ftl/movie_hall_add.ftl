<html>
    <head>
        <title>${AddMovieHallKey}</title>
    </head>
    <body>
        <h1>${AddMovieHallKey}</h1>
        <a href=${adminPanelNavigationPathKey}>${backToAdminPanelKey}</a>
        <form method="post">
            <label for="movie_hall_name">Movie hall name:</label><br>
            <input type="text" id="movie_hall_name" name="id"><br>
            <label for="number_of_seats">Number of seats:</label><br>
            <input type="number" id="number_of_seats" name="numberOfSeats"><br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>