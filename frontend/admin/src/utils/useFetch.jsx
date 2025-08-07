import { useState, useCallback } from 'react';
import axios from 'axios';

function useFetch() {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = useCallback(async (url, method = 'get', body = null) => {
        setLoading(true);
        setError(null);
        try {
            const config = {
                method: method.toLowerCase(),
                url,
                data: body,
            };
            const response = await axios(config);
            setData(response.data);
            return response;
        } catch (err) {
            setError(err);
            throw err;
        } finally {
            setLoading(false);
        }
    }, []);

    return { data, loading, error, fetchData };
}

export default useFetch;