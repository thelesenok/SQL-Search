import axios from 'axios';

const SearchService = {};

SearchService.search = (query) => {
    return new Promise(resolve => {
        axios.post('/search', query)
            .then(response => {
                resolve(response.data);
            });
    });
};

export default SearchService;