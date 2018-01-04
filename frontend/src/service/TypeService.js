import axios from 'axios';

const TypeService = {};

TypeService.getAllTypes = () => {
    return new Promise((resolve) => {
        axios.get('/types')
            .then(response => {
                resolve(response.data);
            });
    });
};

export default TypeService;