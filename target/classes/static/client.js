function uploadFile() {
    const usernameInput = document.getElementById('username');
    const signalFileInput = document.getElementById('signalFile');
    const algorithmChoice = document.getElementById("algorithm").value;

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

    const uniqueFileName = `user_${username}_${Date.now()}_${signalFile.name}`;

    const formData = new FormData();
    formData.append('username', username);
    formData.append('file', signalFile);
    formData.append('algorithmChoice', algorithmChoice);
    formData.append('uniqueFileName', uniqueFileName);

    fetch('/upload', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log('File uploaded successfully:', data);
    })
    .catch(error => {
        console.error('Error uploading file:', error);
    });

    fetch('/reconstruct', {
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
}