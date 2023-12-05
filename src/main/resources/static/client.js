function uploadFile() {
    const usernameInput = document.getElementById('username');
    const signalFileInput = document.getElementById('signalFile');
    const username = usernameInput.value;

    if (!username) {
        alert('Insira nome de usuário.');
        return;
    }

    const signalFile = signalFileInput.files[0];

    if (!signalFile) {
        alert('Selecione um arquivo CSV para o vetor de sinal.');
        return;
    }

    const formData = new FormData();
    formData.append('username', username);
    formData.append('signalFile', signalFile);

    fetch('/upload')
        .then(response => response.json())
        .then(data => {
            // Adiciona a matriz H aleatória ao FormData
            formData.append('modelMatrix', new Blob([data.matrix.map(row => row.join(',')).join('\n')], { type: 'text/csv' }));

            // Envia a requisição para o servidor
            fetch('/api/reconstruct', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                console.log('Image reconstructed successfully:', data);
            })
            .catch(error => {
                console.error('Error reconstructing image:', error);
            });
        })
        .catch(error => {
            console.error('Error getting random model matrix:', error);
        });
}