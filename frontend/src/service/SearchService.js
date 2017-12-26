const SearchService = {};

SearchService.search = (query) => {
    // dummy response
    return new Promise(resolve => {
        const dummyData = {
            rows: [
                [
                    "ID",
                    "Title",
                    "Some attribute",
                    "Weight"
                ]
            ]
        };
        for (let i = 0; i < 50; i++) {
            dummyData.rows.push([
                i,
                ("Title " + i),
                ("Attribute " + i),
                ("Weight " + i)
            ]);
        }

        setTimeout(() => {
            resolve(dummyData);
        }, 500);
    });
};

export default SearchService;