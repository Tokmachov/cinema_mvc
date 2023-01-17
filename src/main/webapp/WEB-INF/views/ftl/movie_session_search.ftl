<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        form {
            background-color: #4654e1;
            width: 50%;
            height: 44px;
            border-radius: 20px;
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-top: 10%;
        }

        input {
            all: unset;
            font: 16px system-ui;
            color: #fff;
            height: 100%;
            width: 100%;
            padding: 6px 10px;
        }
        ::placeholder {
            color: #fff;
            opacity: 0.7;
        }

        button {
            all: unset;
            cursor: pointer;
            width: 44px;
            height: 44px;
        }

        svg {
            color: #fff;
            fill: currentColor;
            width: 24px;
            height: 24px;
            padding: 10px;
        }
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            height: 100%;
            background-color: orange;
        }

        .searchResults {
            max-width: 80%;
        }

        div.gallery {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 180px;
        }

        div.gallery:hover {
            border: 1px solid #777;
        }

        div.gallery img {
            width: 100%;
            height: auto;
        }

        div.gallery > div {
            padding: 15px;
            text-align: center;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
    <form role="search" id="form">
        <button>
            <svg viewBox="0 0 1024 1024"><path class="path1" d="M848.471 928l-263.059-263.059c-48.941 36.706-110.118 55.059-177.412 55.059-171.294 0-312-140.706-312-312s140.706-312 312-312c171.294 0 312 140.706 312 312 0 67.294-24.471 128.471-55.059 177.412l263.059 263.059-79.529 79.529zM189.623 408.078c0 121.364 97.091 218.455 218.455 218.455s218.455-97.091 218.455-218.455c0-121.364-103.159-218.455-218.455-218.455-121.364 0-218.455 97.091-218.455 218.455z"></path></svg>
        </button>
        <input type="search" id="inputId" name="q"
        placeholder="Search..."
        aria-label="Search through site content">
    </form>
    <a href="${adminPanelNavigationPathKey}">${backToAdminPanelKey}</a>
    <div class="searchResults">
    </div>
</body>
</html>

<script>
    let searchResultsDivElement = document.querySelector('.searchResults');
    $('input').keyup(function() {
        let query = $(this).val();
        let searchQueryURL = createSearchQueryURL(query);
        $.getJSON(searchQueryURL, function(data) {
            searchResultsDivElement.innerHTML = '';
            for (var i = 0; i < data.length; i++) {
                let sessionId = data[i].sessionId;
                let posterId = data[i].posterId;
                let movieTitle = data[i].movieTitle;
                let movieHallTitle = data[i].movieHallTitle;
                let date = data[i].date;
                let time = data[i].time;
                let searchResultElement = createMovieSessionInfoElement(posterId, sessionId, movieTitle, movieHallTitle, date, time);
                searchResultsDivElement.appendChild(searchResultElement);
            }
        });
    });

    let createSearchQueryURL = function(query) {
        let searchUrl = document.URL.substring(0, document.URL.lastIndexOf('/'));
        let url = new URL(searchUrl);
        url.searchParams.append('query', query);
        return url.toString();
    }

    let createMovieSessionInfoElement = function(posterId, sessionId, movieTitle, movieHallTitle, date, time) {
        let posterUrl = '${moviePosterBaseUrlKey}' + '/' + posterId;
        let sessionPageUrl = '${sessionPageBaseUrlKey}' + '/' + sessionId;

        let galleryElement = document.createElement('div');
        galleryElement.className = 'gallery';

        let imgLink = document.createElement('a');
        imgLink.href = sessionPageUrl;

        let imgElement = document.createElement('img');
        imgElement.src = posterUrl;
        imgElement.alt = 'I\' blind!'

        let movieNameDivElement = document.createElement('div');
        movieNameDivElement.innerHTML = 'Movie title: ' + movieTitle;

        let hallNameDivElement = document.createElement('div');
        hallNameDivElement.innerHTML = 'Hall: ' + movieHallTitle;

        let dateDivElement = document.createElement('div');
        dateDivElement.innerHTML = 'Date: ' + date;

        let timeDivElement = document.createElement('div');
        timeDivElement.innerHTML = 'Time: ' + time;

        imgLink.appendChild(imgElement);
        galleryElement.appendChild(imgLink);
        galleryElement.appendChild(movieNameDivElement);
        galleryElement.appendChild(hallNameDivElement);
        galleryElement.appendChild(dateDivElement);
        galleryElement.appendChild(timeDivElement);
        return galleryElement;
    }

</script>