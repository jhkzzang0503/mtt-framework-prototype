import React, { useState, useEffect } from 'react';
import useBuilderStore from '@/store';
import useFetch from '@/utils/useFetch'

const ModulesSet = ({ selectedModule, onStyleChange }) => {
  const [bootstrapClasses, setBootstrapClasses] = useState('');
  const [customStyles, setCustomStyles] = useState('');
  const [className, setClassName] = useState('');

  const [modalOpen, setModalOpen] = useState(false);
  const [filePath, setFilePath] = useState('');
  const [fileName, setFileName] = useState('');

  const handleGenerate = () => setModalOpen(true);
  const { items } = useBuilderStore();
  const { data, loading, error, fetchData } = useFetch();

  useEffect(() => {
    if (selectedModule && selectedModule.styles) {
      setBootstrapClasses(selectedModule.styles.bootstrapClasses || '');
      setCustomStyles(JSON.stringify(selectedModule.styles.customStyles, null, 2) || '');
      setClassName(selectedModule.styles.className || '');
    } else {
      setBootstrapClasses('');
      setCustomStyles('');
      setClassName('');
    }
  }, [selectedModule]);

  const handleCreateFile = async () => {
    const imports = items.map(item => `import ${item.type.split('/').pop()} from '${item.path}';`).join('\n');
    console.log("items :: ", items)
    const components = items.map(item => {
      const styleProp = item.styles?.customStyles ? `style={${JSON.stringify(item.styles.customStyles)}}` : '';
      const classProp = `className="${item.styles?.bootstrapClasses || ''} ${item.styles?.className || ''}"`.trim();
      return `<${item.type.split('/').pop()} ${classProp} ${styleProp} />`;
    }).join('\n');

    const fileContent = `
            ${imports}
            const ${fileName}Component = () => {
              return (
                <div class="row">
                  ${components}
                </div>
              );
            };
            
            export default ${fileName}Component;
        `;

    console.log(JSON.stringify({
      file_path: `/${filePath}/${fileName}.jsx`,
      file_content: fileContent,
    }));

    try {
      let postData = {
        "filePath": filePath,
        "fileName": `${fileName}.jsx`,
        "fileContent": fileContent
      }
      const response = await fetchData('/api/admin/postTest', 'post', postData);

      const result = response.data;
      console.log("API 호출 성공:", result);
      alert(result['message'])


    } catch (err) {
      console.error("API 호출 실패:", err);
    } finally {
      console.log("fileContent : ", fileContent);
      setModalOpen(false);
    }
  };

  const handleStyleChange = () => {
    if (onStyleChange) {
      let parsedCustomStyles = {};
      try {
        parsedCustomStyles = customStyles ? JSON.parse(customStyles) : {};
      } catch (error) {
        console.error("Invalid JSON in custom styles:", error);
      }

      onStyleChange({
        bootstrapClasses,
        customStyles: parsedCustomStyles,
        className,
      });
    }
  };

  return (
      <div className="style-editor-panel">
        <h2>Modules Style Settings</h2>
        {selectedModule && (
            <div>
              <h3>{selectedModule.name}</h3>
              <div className="form-group">
                <label htmlFor="bootstrapClasses">Bootstrap Classes:</label>
                <input
                    type="text"
                    id="bootstrapClasses"
                    value={bootstrapClasses}
                    onChange={(e) => setBootstrapClasses(e.target.value)}
                    onBlur={handleStyleChange}
                />
              </div>
              <div className="form-group">
                <label htmlFor="customStyles">Custom Styles (JSON):</label>
                <textarea
                    id="customStyles"
                    value={customStyles}
                    onChange={(e) => setCustomStyles(e.target.value)}
                    onBlur={handleStyleChange}
                    rows={5}
                />
              </div>
              <div className="form-group">
                <label htmlFor="className">Custom Class Name:</label>
                <input
                    type="text"
                    id="className"
                    value={className}
                    onChange={(e) => setClassName(e.target.value)}
                    onBlur={handleStyleChange}
                />
              </div>
            </div>
        )}
        {modalOpen && (
            <div className="modal-overlay"> {/* 모달 오버레이에 클래스 추가 */}
              <div className="modal-content" style={{background: "var(--bs-body-bg)"}}> {/* 모달 콘텐츠에 클래스 추가 */}
                <input type="text" value={filePath} onChange={e => setFilePath(e.target.value)}
                       placeholder="경로 (예: components/generated)" className="modal-input"/><br/> {/* 입력 필드에 클래스 추가 */}
                <input type="text" value={fileName} onChange={e => setFileName(e.target.value)}
                       placeholder="파일명 (예: MyNewComponent)" className="modal-input"/><br/> {/* 입력 필드에 클래스 추가 */}
                <div className="modal-actions"> {/* 모달 버튼 래퍼에 클래스 추가 */}
                  <button onClick={() => setModalOpen(false)} className="btn btn-secondary modal-btn-cancel">취소</button> {/* 취소 버튼에 클래스 추가 */}
                  <button onClick={handleCreateFile} className="btn btn-primary modal-btn-generate">생성</button> {/* 생성 버튼에 클래스 추가 */}
                </div>
              </div>
            </div>
        )}
        <button onClick={handleGenerate} className="generate-btn">생성</button>
      </div>
  );
};

export default ModulesSet;