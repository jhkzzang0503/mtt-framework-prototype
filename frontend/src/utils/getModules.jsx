// src/utils/getModules.jsx
import React, { Suspense } from 'react';

const getModules = () => {
  const modules = {};
  const modulesToImport = import.meta.glob('../components/modules/**/*.jsx');

  Object.keys(modulesToImport).forEach((key) => {
    const moduleName = key.replace('../components/modules/', '').replace(/\.jsx$/, '');
    const modulePath = `../components/modules/${moduleName}`;
    const pathParts = moduleName.split('/');
    const categoryName = pathParts[0];
    const componentName = pathParts.slice(1).join('/');

    if (!modules[categoryName]) {
      modules[categoryName] = [];
    }

    modules[categoryName].push({
      id: moduleName,
      name: componentName,
      path: modulePath,
      // React.lazy 컴포넌트가 아닌, import 함수 자체를 전달
      componentImportFunc: modulesToImport[key],
    });
  });

  return modules;
};

export default getModules;