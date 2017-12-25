const TypeService = {};

TypeService.getAllTypes = () => {
    return new Promise((resolve) => {
        const values = [];
        values.push({
            value: 1,
            label: 'Type 1'
        }, {
            value: 2,
            label: 'Type 2'
        });
        resolve(values);
    });
};

export default TypeService;