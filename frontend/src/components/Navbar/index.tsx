
import { ReactComponent as GithubIcon } from 'assets/img/github.svg';
import './styles.css';

function NavBar() {
    return (
        <header>
            <nav className="container">
                <div className="dsmovie-nav-component">
                    <h1>DSMovie</h1>
                    <a href="https://github.com/romullo-pereira">
                        <div className="dsmovie-contact-container">
                            <GithubIcon />
                            <p className="dsmovie-contact-link">/romullo-pereira</p>
                        </div>
                    </a>

                </div>
            </nav>
        </header>
    );
}

export default NavBar;