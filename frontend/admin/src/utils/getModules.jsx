import React, { Suspense } from 'react';

const getModules = () => {
  const modules = {};
  const modulesToImport = import.meta.glob('@web/components/modules/**/*.jsx');
  const webToImport = import.meta.glob('@web')

  let webPath
  Object.keys(webToImport).forEach((key) => {
    const lastSlashIndex = key.lastIndexOf('/');
    const directoryPath = key.slice(0, lastSlashIndex + 1);
    webPath = directoryPath
  })

  Object.keys(modulesToImport).forEach((key) => {
    const moduleName = key.replace(`${webPath}components/modules/`, '').replace(/\.jsx$/, '');
    const modulePath = `@web/components/modules/${moduleName}`;
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
      componentImportFunc: modulesToImport[key],
    });
  });

  return modules;
};

export default getModules;