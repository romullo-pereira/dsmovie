
import axios from "axios";
import MovieCard from "components/MovieCard";
import Pagination from "components/Pagination";
import { Fragment, useEffect, useState } from "react";
import { MoviePage } from "types/movie";
import { BASE_URL } from "utils/requests";

function Listing() {

    const [pageNumber, setPageNumber] = useState(0);

    const [page, setPage] = useState<MoviePage>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 12,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true,
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/movies?page=${pageNumber}&size=12&sort=id`)
            .then(response => {
                const data = response.data as MoviePage;
                setPage(data);
            });
    }, [pageNumber])

    const handlePageChange = (newPageNumber : number) =>  {
        setPageNumber(newPageNumber);
    }

    return (
        <Fragment>
            <Pagination page={page} onChange={handlePageChange}/>
            <div className="container">
                <div className="row">
                    {page.content.map(movie => (
                        <div key={movie.id} className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                            <MovieCard  movie={movie} />
                        </div>
                    ))}

                </div>
            </div>
        </Fragment>
    );
}

export default Listing;