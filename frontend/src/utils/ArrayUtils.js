const ArrayUtils = {};

ArrayUtils.update = (sourceArray, selector, merge) => {
    const updated = sourceArray.map(item => {
        if (selector(item)) {
            return Object.assign(item, merge);
        } else {
            return Object.assign(item, {});
        }
    });
    return updated;
};

export default ArrayUtils;