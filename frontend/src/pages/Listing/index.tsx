
import MovieCard from "components/MovieCard";
import Pagination from "components/Pagination";
import { Fragment } from "react";

function Listing() {
    return (
        <Fragment>
            <Pagination />
            <div className="container">
                <div className="row">
                    <div className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>

                    <div className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>

                    <div className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>

                    <div className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>

                    <div className="col-sm-3 col-lg-4 col-xl-3 mb-3">
                        <MovieCard />
                    </div>
                </div>
            </div>
        </Fragment>
    );
}

export default Listing;