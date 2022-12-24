<!DOCTYPE html>

<html>
<head>
    <style>
        div.gallery {
            border: 1px solid #ccc;
        }

        div.gallery:hover {
            border: 1px solid #777;
        }

        div.gallery img {
            width: 100%;
            height: auto;
        }

        div.desc {
            padding: 15px;
            text-align: center;
        }

        * {
            box-sizing: border-box;
        }

        .responsive {
            padding: 0 6px;
            float: left;
            width: 24.99999%;
        }

        @media only screen and (max-width: 700px) {
            .responsive {
                width: 49.99999%;
                margin: 6px 0;
            }
        }

        @media only screen and (max-width: 500px) {
            .responsive {
                width: 100%;
            }
        }

        .clearfix:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>

</head>

<body>
    <h1>List of movies</h1>
    <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
    <br>
    <form method="post">
        <#list movieListModelKey as movie>
            <div class="responsive">
                <div class="gallery">
                    <a target="_blank" href="movies/poster/${movie.posterId}">
                        <img src="movies/poster/${movie.posterId}" alt="poster for movie ${movie.title}" width="3000" height="3000">
                    </a>
                    <div class="desc">${movie.title}</div>
                    <div class="desc">${movie.description}</div>
                    <div class="desc"><input type="checkbox" name="movieIdList" value="${movie.title}"></div>
                </div>
            </div>
        </#list>

        <div class="clearfix"></div>

        <div style="padding:6px;">
            <p><input type="submit" value="Delete selected movies"></p>
        </div>
    </form>
</body>
</html>